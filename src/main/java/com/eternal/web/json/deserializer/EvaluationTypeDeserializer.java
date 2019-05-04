package com.eternal.web.json.deserializer;

import com.eternal.web.message.MessageCode;
import com.eternal.web.type.EvaluationType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * {@link EvaluationTypeDeserializer}
 *
 * @author taiki0304
 */
public class EvaluationTypeDeserializer extends JsonDeserializer<EvaluationType> {

    @Override
    public EvaluationType deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return EvaluationType.get(jsonParser.getValueAsString())
                .orElseThrow(() -> new JsonParseException(jsonParser, MessageCode.TYPE_MISMATCH_EXCEPTION));
    }
}
