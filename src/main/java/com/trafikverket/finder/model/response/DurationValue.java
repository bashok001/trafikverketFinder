package com.trafikverket.finder.model.response;

import java.time.ZonedDateTime;

public record DurationValue(ZonedDateTime start, ZonedDateTime end) {
}
