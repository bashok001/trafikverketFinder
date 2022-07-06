package com.trafikverket.finder;

import com.trafikverket.finder.model.request.BookingSessionRequest;
import com.trafikverket.finder.model.request.BundleQueryRequest;
import com.trafikverket.finder.model.request.ScheduleRequest;
import com.trafikverket.finder.model.response.DataList;
import com.trafikverket.finder.model.response.Occasions;
import com.trafikverket.finder.model.response.ScheduleResponse;
import com.trafikverket.finder.model.response.ScheduleResponseData;
import com.trafikverket.finder.notifier.Notifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.trafikverket.finder.Constants.AUTOMATIC_VEHICLE_TYPE_ID;
import static com.trafikverket.finder.Constants.LICENSE_ID;
import static com.trafikverket.finder.Constants.LOCATION_ID_ENKOPING;
import static com.trafikverket.finder.Constants.LOCATION_ID_ESKILSTUNA;
import static com.trafikverket.finder.Constants.LOCATION_ID_SOLLENTUNA;
import static com.trafikverket.finder.Constants.LOCATION_ID_UPPSALA;
import static com.trafikverket.finder.Constants.PNO;
import static com.trafikverket.finder.Constants.SEARCH_END_DATE;
import static com.trafikverket.finder.Constants.SEARCH_START_DATE;
import static com.trafikverket.finder.Constants.TRAFIKVERKET_BOOKING_URL;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@Slf4j
public class Poller {

  private final DateFormat df = DateFormat.getDateInstance();
  @Qualifier("mailNotifier")
  @Autowired
  private Notifier notifier;
  @Value("${spring.mail.username}")
  private String emailAddress;

  @Scheduled(cron = "${frequency.of.poller}")
  public void getCurrentTimes() throws Exception {
    var message = Stream.of(LOCATION_ID_SOLLENTUNA, LOCATION_ID_UPPSALA, LOCATION_ID_ENKOPING, LOCATION_ID_ESKILSTUNA)
                        .map(this::sendRequest)
                        .filter(Objects::nonNull)
                        .map(ScheduleResponse::data)
                        .map(ScheduleResponseData::bundles)
                        .flatMap(List::stream)
                        .map(DataList::occasions)
                        .flatMap(List::stream)
                        .filter(this::filterDates)
                        .map(this::toText)
                        .collect(joining("; "));

    if (!message.isBlank())
      notifier.send(emailAddress, message);
  }

  private String toText(Occasions occasions) {
    return String.format("Location: %s, Date: %s, Time: %s", occasions.locationName(), occasions.date(), occasions.time());
  }

  private boolean filterDates(Occasions occasions) {
    return LocalDate.parse(occasions.date()).isBefore(LocalDate.parse(SEARCH_END_DATE));
  }

  private ScheduleResponse sendRequest(int locationId) {
    var bookingSession = new BookingSessionRequest(PNO, LICENSE_ID, 0, false, false, 0, List.of(), 0, false, null, null, 0);
    var bundleQuery = new BundleQueryRequest(SEARCH_START_DATE, 0, locationId, 0,
                                             AUTOMATIC_VEHICLE_TYPE_ID, 1, 1, 12);
    var body = new ScheduleRequest(bookingSession, bundleQuery);

    HttpClient httpClient;
    // To Debug
    // httpClient = HttpClient.create().wiretap("reactor.netty.http.client.HttpClient", DEBUG, TEXTUAL);

    // General
    httpClient = HttpClient.create();

    return WebClient.builder()
                    .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1000000))
                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                    .baseUrl(TRAFIKVERKET_BOOKING_URL)
                    .build()
                    .post()
                    .uri("/Boka/occasion-bundles")
                    .contentType(APPLICATION_JSON)
                    .body(Mono.just(body), ScheduleRequest.class)
                    .retrieve()
                    .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new Exception("Error in response from trafikverket. " + body)))
                    .bodyToMono(ScheduleResponse.class)
                    .block();
  }
}
