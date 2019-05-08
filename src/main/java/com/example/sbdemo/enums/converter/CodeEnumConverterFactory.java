package com.example.sbdemo.enums.converter;

import com.example.sbdemo.enums.CodeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * code值转枚举（JSON数据不转换）
 */

@SuppressWarnings("unchecked")
public class CodeEnumConverterFactory implements ConverterFactory<String, CodeEnum> {
    /**
     * 缓存对应枚举的converter的Map
     */
    private static final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @Override
    public <T extends CodeEnum> Converter<String, T> getConverter(Class<T> aClass) {
        Converter<String, T> converter = converterMap.get(aClass);
        if (converter == null) {
            converter = new CodeConverterToEnum<>(aClass);
            converterMap.put(aClass, converter);
        }
        return converter;
    }

    class CodeConverterToEnum<T extends CodeEnum> implements Converter<String, T> {
        private final Class<T> enumType;

        CodeConverterToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String s) {
            T[] enumConstants = this.enumType.getEnumConstants();
            for (T e : enumConstants) {
                if (Integer.valueOf(s).equals(e.getCode())) {
                    return e;
                }
            }
            return null;
        }
    }
}
