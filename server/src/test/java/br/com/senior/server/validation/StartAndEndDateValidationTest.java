package br.com.senior.server.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StartAndEndDateValidationTest {

    @Test
    void validate() {
        var startDate = LocalDateTime.of(1999, 11, 7, 5, 30, 7);
        var endDate = LocalDateTime.of(1998, 11, 7, 5, 30, 7);
        assertThrows(ValidationException.class, () -> StartAndEndDateValidation.validate(startDate, endDate));

        var endDate2 = LocalDateTime.of(2000, 11, 7, 5, 30, 7);
        assertDoesNotThrow(() -> StartAndEndDateValidation.validate(startDate, endDate2));
    }

    @Test
    void validateWithEndDateNull() {
        assertDoesNotThrow(() -> StartAndEndDateValidation.validate(LocalDateTime.now(), null));
    }
}
