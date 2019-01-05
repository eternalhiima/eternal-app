package com.eternal.web.json.selializer;

import java.io.IOException;
import com.eternal.web.domain.type.SortType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SortTypeSelializer extends JsonSerializer<SortType> {

    @Override
    public void serialize(SortType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(convert(value));
    }

    private String convert(SortType value) {
        return value.getKey();
    }

}