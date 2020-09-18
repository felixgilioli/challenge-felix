package br.com.senior.server.checkin;

import java.math.BigDecimal;

public interface CheckinFinalValueCalculator {

    BigDecimal calculate(Checkin checkin);
}
