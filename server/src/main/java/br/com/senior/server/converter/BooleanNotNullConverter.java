package br.com.senior.server.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanNotNullConverter implements AttributeConverter<Boolean, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(Boolean value) {
        if (value == null) {
            return false;
        }
        return value;
    }

    @Override
    public Boolean convertToEntityAttribute(Boolean value) {
        return value;
    }

}
