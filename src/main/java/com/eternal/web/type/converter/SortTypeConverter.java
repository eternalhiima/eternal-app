package com.eternal.web.type.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.eternal.web.message.MessageCode;
import com.eternal.web.type.SortType;

/** {@link SortTypeConverter} */
@Component
public class SortTypeConverter implements Converter<String, SortType> {

    @Override
    public SortType convert(String source) {
        return SortType.get(source)
                .orElseThrow(() -> new IllegalArgumentException(MessageCode.TYPE_MISMATCH_EXCEPTION));
    }
}
