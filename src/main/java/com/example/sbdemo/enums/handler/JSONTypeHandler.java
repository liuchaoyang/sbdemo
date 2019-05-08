package com.example.sbdemo.enums.handler;

import com.example.sbdemo.model.School;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JSONTypeHandler extends BaseTypeHandler<School> {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, School parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, toJson(parameter));  //java对象转化成json字符串
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public School getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName), School.class);
    }

    @Override
    public School getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex), School.class);
    }

    @Override
    public School getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex), School.class);
    }

    private String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private School toObject(String content, Class<?> clazz) {
        if (content != null && !content.isEmpty()) {
            try {
                return (School) mapper.readValue(content, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}