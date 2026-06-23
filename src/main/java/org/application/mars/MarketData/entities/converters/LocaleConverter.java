package org.application.mars.MarketData.entities.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.application.mars.MarketData.models.Massive.enums.Locale;

@Converter(autoApply = true)
public class LocaleConverter implements AttributeConverter<Locale, String> {

    @Override
    public String convertToDatabaseColumn(Locale locale) {
        return locale != null ? locale.getValue() : null;
    }

    @Override
    public Locale convertToEntityAttribute(String dbValue) {
        return dbValue != null ? Locale.from(dbValue) : null;
    }
}
