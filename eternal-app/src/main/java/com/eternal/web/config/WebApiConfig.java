package com.eternal.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.eternal.web.domain.type.SortType;
import com.eternal.web.json.deselializer.SortTypeDesilializer;
import com.eternal.web.json.selializer.SortTypeSelializer;
import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
public class WebApiConfig {


    /**
     * JacksonのObjectMapper
     *
     * @return ObjectMapper
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        // nullの項目を出力しない
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        // 独自のEnumのSerializer
        builder.serializerByType(SortType.class, new SortTypeSelializer());

        // 独自のEnumのDesilializer
        builder.deserializerByType(SortType.class, new SortTypeDesilializer());
        return builder;
    }
}
