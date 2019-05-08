package com.example.sbdemo.enums.serialization;

import com.example.sbdemo.enums.CodeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 枚举值序列化时转code
 */
public class CodeEnumSerializer extends JsonSerializer<CodeEnum<Integer>> {
    @Override
    public void serialize(CodeEnum<Integer> codeEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(codeEnum.getCode());
    }
}
