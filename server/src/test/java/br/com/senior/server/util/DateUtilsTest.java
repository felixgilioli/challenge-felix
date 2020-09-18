package br.com.senior.server.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateUtilsTest {
    
    @Test
    void isWeekend() {
        var saturday = LocalDateTime.of(2020, 9, 19, 0, 0, 0);
        assertTrue(DateUtils.isWeekend(saturday));

        var wednesday = LocalDateTime.of(2020, 9, 17, 0, 0, 0);
        assertFalse(DateUtils.isWeekend(wednesday));
    }
}
