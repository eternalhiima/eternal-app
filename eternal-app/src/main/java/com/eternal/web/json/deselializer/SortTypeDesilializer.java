package com.eternal.web.json.deselializer;

import java.io.IOException;
import com.eternal.web.domain.type.SortType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class SortTypeDesilializer extends JsonDeserializer<SortType> {

    @Override
    public SortType deserialize(JsonParser jsonPerser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        return convert(jsonPerser.getValueAsString());
    }

    private SortType convert(String value) {
        return SortType.get(value);
    }

}
