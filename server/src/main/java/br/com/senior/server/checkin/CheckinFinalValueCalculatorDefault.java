package br.com.senior.server.checkin;

import com.felix.common.date.Dates;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static br.com.senior.server.util.DateUtils.isWeekend;

public class CheckinFinalValueCalculatorDefault implements CheckinFinalValueCalculator {

    private static final BigDecimal DAILY_ON_WEEKDAYS = new BigDecimal(120);
    private static final BigDecimal DAILY_ON_WEEKENDS = new BigDecimal(150);
    private static final BigDecimal PARKING_SPACE_ON_WEEKDAYS = new BigDecimal(15);
    private static final BigDecimal PARKING_SPACE_ON_WEEKENDS = new BigDecimal(20);
    private static final LocalTime DAILY_END_TIME_UTC = LocalTime.of(19, 30);

    @Override
    public BigDecimal calculate(Checkin checkin) {
        if (checkin.getDepartureTime() == null)
            return null;

        var finalValue = BigDecimal.ZERO;

        if (checkin.getEntryTime().toLocalDate().equals(checkin.getDepartureTime().toLocalDate())) {
            return addDaily(checkin, finalValue, checkin.getEntryTime());
        }

        List<LocalDateTime> hostingDays = Dates
                .getInterval(getStartOfNextDay(checkin), checkin.getDepartureTime(), ChronoUnit.DAYS);

        for (LocalDateTime day : hostingDays) {
            finalValue = addDaily(checkin, finalValue, day);
        }

        if (checkin.getDepartureTime().toLocalTime().isAfter(DAILY_END_TIME_UTC)) {
            finalValue = addDaily(checkin, finalValue, checkin.getDepartureTime());
        }

        return finalValue;
    }

    private LocalDateTime getStartOfNextDay(Checkin checkin) {
        return checkin.getEntryTime().plusDays(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    private BigDecimal addDaily(Checkin checkin, BigDecimal finalValue, LocalDateTime departureTime) {
        if (isWeekend(departureTime)) {
            finalValue = finalValue.add(DAILY_ON_WEEKENDS);
            if (checkin.getHasVehicle()) {
                finalValue = finalValue.add(PARKING_SPACE_ON_WEEKENDS);
            }
        } else {
            finalValue = finalValue.add(DAILY_ON_WEEKDAYS);
            if (checkin.getHasVehicle()) {
                finalValue = finalValue.add(PARKING_SPACE_ON_WEEKDAYS);
            }
        }
        return finalValue;
    }
}
