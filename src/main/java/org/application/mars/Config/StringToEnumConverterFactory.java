package org.application.mars.Config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

//converts lowercase enums to uppercase
@Component
public class StringToEnumConverterFactory
        implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {

        return source -> {
            if (source == null) return null;
            System.out.println("Converted: " + Enum.valueOf(targetType, source.toUpperCase()));
            return (T) Enum.valueOf(targetType, source.toUpperCase());
        };
    }
}