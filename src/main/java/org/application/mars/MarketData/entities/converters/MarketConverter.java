package org.application.mars.MarketData.entities.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.application.mars.MarketData.models.Massive.enums.Market;

@Converter(autoApply = true)
public class MarketConverter implements AttributeConverter<Market, String> {

    @Override
    public String convertToDatabaseColumn(Market market) {
        return market != null ? market.getName() : null;
    }

    @Override
    public Market convertToEntityAttribute(String dbValue) {
        return dbValue != null ? Market.from(dbValue) : null;
    }
}
