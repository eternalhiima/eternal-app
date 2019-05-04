package com.eternal.web.json.deserializer;

import java.io.IOException;

import com.eternal.web.message.MessageCode;
import com.eternal.web.type.SortType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * {@link SortTypeDeserializer}
 *
 * @author taiki0304
 */
public class SortTypeDeserializer extends JsonDeserializer<SortType> {

    @Override
    public SortType deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return SortType.get(jsonParser.getValueAsString())
                .orElseThrow(() -> new JsonParseException(jsonParser, MessageCode.TYPE_MISMATCH_EXCEPTION));
    }
}
