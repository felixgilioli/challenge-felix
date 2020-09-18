package br.com.senior.server.checkin;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CheckinFinalValueCalculatorDefaultTest {

    @Test
    void calculate() {
        var calculator = new CheckinFinalValueCalculatorDefault();
        assertNull(calculator.calculate(new Checkin()));

        var checkin1 = new Checkin();
        checkin1.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin1.setDepartureTime(LocalDateTime.of(2020, 9, 20, 17, 0, 0));
        checkin1.setHasVehicle(true);
        assertEquals(new BigDecimal("475"), calculator.calculate(checkin1));

        var checkin2 = new Checkin();
        checkin2.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin2.setDepartureTime(LocalDateTime.of(2020, 9, 20, 17, 0, 0));
        assertEquals(new BigDecimal("420"), calculator.calculate(checkin2));

        var checkin3 = new Checkin();
        checkin3.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin3.setDepartureTime(LocalDateTime.of(2020, 9, 17, 17, 0, 0));
        assertEquals(new BigDecimal("120"), calculator.calculate(checkin3));

        var checkin4 = new Checkin();
        checkin4.setEntryTime(LocalDateTime.of(2020, 9, 17, 7, 0, 0));
        checkin4.setDepartureTime(LocalDateTime.of(2020, 9, 17, 10, 0, 0));
        checkin4.setHasVehicle(true);
        assertEquals(new BigDecimal("135"), calculator.calculate(checkin4));
    }
}
