package com.example.sbdemo.enums.handler;

import com.example.sbdemo.enums.CodeEnum;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import sun.misc.SharedSecrets;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(CodeEnum.class)
public class CodeEnumHandler<E extends Enum<E> & CodeEnum<Integer>> extends EnumTypeHandler<E> {
    private final E[] enums;

    public CodeEnumHandler(Class<E> type) {
        super(type);
        this.enums = SharedSecrets.getJavaLangAccess().getEnumConstantsShared(type);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E iEnumCode, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, iEnumCode.getCode());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parse(resultSet.getInt(s));
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parse(resultSet.getInt(i));
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parse(callableStatement.getInt(i));
    }

    private E parse(Integer code) {
        if (null != code) {
            for (E e : enums) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }
}
