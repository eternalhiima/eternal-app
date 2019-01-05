package com.eternal.web.json.deselializer;

import java.io.IOException;
import java.util.Objects;
import com.eternal.web.domain.type.SortType;
import com.eternal.web.message.MessageCode;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * SortTypeDesilializer
 * @author taiki0304
 *
 */
public class SortTypeDesilializer extends JsonDeserializer<SortType> {

    @Override
    public SortType deserialize(JsonParser jsonPerser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        SortType sortType = SortType.get(jsonPerser.getValueAsString());
        if (Objects.isNull(sortType)) {
            throw new JsonParseException(jsonPerser, MessageCode.API0001E);
        }
        return sortType;
    }
}
