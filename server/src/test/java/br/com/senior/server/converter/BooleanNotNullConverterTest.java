package br.com.senior.server.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanNotNullConverterTest {

    @Test
    void convertToDatabaseColumnTest() {
        var converter = new BooleanNotNullConverter();
        assertFalse(converter.convertToDatabaseColumn(null));
        assertFalse(converter.convertToDatabaseColumn(false));
        assertTrue(converter.convertToDatabaseColumn(true));
    }
}
