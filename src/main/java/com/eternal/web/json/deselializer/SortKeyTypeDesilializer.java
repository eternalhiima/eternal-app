package com.eternal.web.json.deselializer;

import java.io.IOException;
import com.eternal.web.message.MessageCode;
import com.eternal.web.type.SortKeyType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * {@link SortKeyTypeDesilializer}
 *
 * @author taiki0304
 */
public class SortKeyTypeDesilializer extends JsonDeserializer<SortKeyType> {

    @Override
    public SortKeyType deserialize(JsonParser jsonPerser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return SortKeyType.get(jsonPerser.getValueAsString())
                .orElseThrow(() -> new JsonParseException(jsonPerser, MessageCode.VALIDATE_EXCEPTION));
    }
}
