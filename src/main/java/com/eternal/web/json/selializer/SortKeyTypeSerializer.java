package com.eternal.web.json.selializer;

import java.io.IOException;
import com.eternal.web.type.SortKeyType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * {@link SortKeyTypeSerializer}
 *
 * @author taiki0304
 *
 */
public class SortKeyTypeSerializer extends JsonSerializer<SortKeyType> {

    @Override
    public void serialize(SortKeyType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(value.getKey());
    }
}
