package com.trafikverket.finder.model.response;

public record Occasions(DurationValue duration, String name, String date, String time, String locationName) {
}
