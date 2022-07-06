package com.trafikverket.finder.model.request;

import java.util.List;

public record BookingSessionRequest(String socialSecurityNumber,
                                    int licenceId,
                                    int bookingModeId,
                                    boolean ignoreDebt,
                                    boolean ignoreBookingHindrance,
                                    int examinationTypeId,
                                    List<String> excludeExaminationCategories,
                                    int rescheduleTypeId,
                                    boolean paymentIsActive,
                                    String paymentReference,
                                    String paymentUrl,
                                    int searchedMonths) {
}
