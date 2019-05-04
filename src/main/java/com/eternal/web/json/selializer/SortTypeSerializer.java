package com.eternal.web.json.selializer;

import java.io.IOException;
import com.eternal.web.type.SortType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * {@link SortTypeSerializer}
 *
 * @author taiki0304
 *
 */
public class SortTypeSerializer extends JsonSerializer<SortType> {

    @Override
    public void serialize(SortType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(value.getKey());
    }
}
