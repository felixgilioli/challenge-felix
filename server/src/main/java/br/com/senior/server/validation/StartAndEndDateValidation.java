package br.com.senior.server.validation;

import java.time.LocalDateTime;

public class StartAndEndDateValidation {

    public static void validate(LocalDateTime start, LocalDateTime end) {
        if (end != null && start.isAfter(end))
            throw new ValidationException("start date is after to end date");
    }
}
