package com.eternal.web.json.deserializer;

import java.io.IOException;

import com.eternal.web.message.MessageCode;
import com.eternal.web.type.SortKeyType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * {@link SortKeyTypeDeserializer}
 *
 * @author taiki0304
 */
public class SortKeyTypeDeserializer extends JsonDeserializer<SortKeyType> {

    @Override
    public SortKeyType deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return SortKeyType.get(jsonParser.getValueAsString())
                .orElseThrow(() -> new JsonParseException(jsonParser, MessageCode.TYPE_MISMATCH_EXCEPTION));
    }
}
