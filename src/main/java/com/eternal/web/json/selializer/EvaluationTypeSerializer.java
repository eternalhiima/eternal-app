package com.eternal.web.json.selializer;

import com.eternal.web.type.EvaluationType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * {@link EvaluationTypeSerializer}
 *
 * @author taiki0304
 *
 */
public class EvaluationTypeSerializer extends JsonSerializer<EvaluationType> {

    @Override
    public void serialize(EvaluationType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(value.getVal());
    }
}
