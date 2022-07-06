package com.trafikverket.finder.notifier;

import com.trafikverket.finder.Constants;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("twilioNotifier")
@Slf4j
public class TwilioWhatsAppNotifier implements Notifier {

  static {
    Twilio.init(Constants.TWILIO_ACCOUNT_SID, Constants.TWILIO_AUTH_TOKEN);
  }

  @Override
  public void send(String destination, String message) {
    var twilioMessage = Message.creator(new PhoneNumber(String.format("whatsapp:%s", destination)),
                                        new PhoneNumber((String.format("whatsapp:%s", Constants.WHATSAPP_FROM_NUMBER))), message)
                               .create();
    log.info("Message Queued to be sent: " + twilioMessage);
  }
}
