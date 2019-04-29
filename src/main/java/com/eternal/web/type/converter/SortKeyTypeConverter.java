package com.eternal.web.type.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.eternal.web.message.MessageCode;
import com.eternal.web.type.SortKeyType;

/** {@link SortKeyTypeConverter} */
@Component
public class SortKeyTypeConverter implements Converter<String, SortKeyType> {

    @Override
    public SortKeyType convert(String source) {
        return SortKeyType.get(source)
                .orElseThrow(() -> new IllegalArgumentException(MessageCode.TYPE_MISMATCH_EXCEPTION));
    }
}
