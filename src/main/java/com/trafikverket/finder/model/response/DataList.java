package com.trafikverket.finder.model.response;

import java.util.List;

public record DataList(List<Occasions> occasions, String cost) {
}
