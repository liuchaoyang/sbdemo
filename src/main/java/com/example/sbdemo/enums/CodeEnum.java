package com.example.sbdemo.enums;

import com.example.sbdemo.enums.serialization.CodeEnumDeserializer;
import com.example.sbdemo.enums.serialization.CodeEnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using = CodeEnumSerializer.class)
@JsonDeserialize(using = CodeEnumDeserializer.class)
public interface CodeEnum<T> {
    T getCode();
}
