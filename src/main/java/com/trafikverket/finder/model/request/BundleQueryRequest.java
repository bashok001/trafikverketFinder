package com.trafikverket.finder.model.request;

public record BundleQueryRequest(String startDate,
                                 int searchedMonths,
                                 int locationId,
                                 int languageId,
                                 int vehicleTypeId,
                                 int tachographTypeId,
                                 int occasionChoiceId,
                                 int examinationTypeId) {
}
