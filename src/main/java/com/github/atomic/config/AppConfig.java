package com.github.atomic.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.atomic.utils.Constants;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class AppConfig {


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(Constants.DATE_TIME_PATTERN_ISO8601_WITH_TIMEZONE);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(Constants.DATE_PATTERN_ISO8601)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN_ISO8601_WITH_TIMEZONE)));
        };
    }
}
