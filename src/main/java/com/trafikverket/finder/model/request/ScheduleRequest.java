package com.trafikverket.finder.model.request;

public record ScheduleRequest(BookingSessionRequest bookingSession,
                              BundleQueryRequest occasionBundleQuery) {
}
