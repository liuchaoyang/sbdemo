package com.example.sbdemo.enums.serialization;

import com.example.sbdemo.enums.CodeEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;

import java.io.IOException;

/**
 * int型code值反序列化时转为枚举
 */
public class CodeEnumDeserializer extends JsonDeserializer<CodeEnum> implements ContextualDeserializer {
    private Enum[] enums;
    private JsonDeserializer deserializer;

    @Override
    public CodeEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken curr = p.getCurrentToken();
        Integer val = null;
        if (curr == JsonToken.VALUE_NUMBER_INT) {
            val = p.getIntValue();
        } else if (curr == JsonToken.VALUE_STRING) {
            try {
                val = Integer.valueOf(p.getText());
            } catch (Exception ignored) {
            }
        }
        if (null != val) {
            for (Enum e : enums) {
                if (val.equals(((CodeEnum) e).getCode())) {
                    return (CodeEnum) e;
                }
            }
            return null;
        }
        return (CodeEnum) deserializer.deserialize(p, ctxt);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        final DeserializationConfig config = ctxt.getConfig();
        final DeserializerFactory factory = ctxt.getFactory();
        final JavaType type = ctxt.getContextualType();
        final BeanDescription beanDesc = config.introspect(type);

        this.enums = ((Class<Enum<?>>) type.getRawClass()).getEnumConstants();
        this.deserializer = factory.createEnumDeserializer(ctxt, type, beanDesc);
        return this;
    }

    @Override
    public boolean isCachable() {
        return true;
    }
}