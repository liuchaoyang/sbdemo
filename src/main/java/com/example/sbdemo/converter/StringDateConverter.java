package com.example.sbdemo.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class StringDateConverter implements Converter<String, Date> {
    private static final Logger logger = LoggerFactory.getLogger(StringDateConverter.class);

    private static final String[] PATTERN = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分ss秒"
    };

    @Override
    public Date convert(String value) {
        Date date = null;
        try {
            date = DateUtils.parseDate(value, PATTERN);
        } catch (Exception e) {
            logger.error("Fail to parse date value {}", value, e);
        }

        return date;
    }

    public static void main(String[] args) throws ParseException {
        final String[] PATTERN = {
                "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
                "yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分ss秒"
        };
        Date date = DateUtils.parseDate("2018-04-01", PATTERN);
        System.out.println(date);
    }
}
