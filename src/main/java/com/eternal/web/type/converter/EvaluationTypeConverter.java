package com.eternal.web.type.converter;

import com.eternal.web.message.MessageCode;
import com.eternal.web.type.EvaluationType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/** {@link EvaluationTypeConverter} */
@Component
public class EvaluationTypeConverter implements Converter<String, EvaluationType> {

    @Override
    public EvaluationType convert(String source) {
        return EvaluationType.get(source)
                .orElseThrow(() -> new IllegalArgumentException(MessageCode.TYPE_MISMATCH_EXCEPTION));
    }
}
