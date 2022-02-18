package com.dewen.typehandler;

import com.dewen.enums.UserStateEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* 数据库中的数据类型 */
@MappedJdbcTypes(JdbcType.INTEGER)

/* 转化后的数据类型 */
@MappedTypes(value = UserStateEnum.class)
public class UserStateTypeHandler extends BaseTypeHandler<UserStateEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserStateEnum userStateEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, userStateEnum.getStateCode());
    }

    @Override
    public UserStateEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        if (code >= 0 && code <= 5) {
            return UserStateEnum.getUserStateByCode(code);
        }
        return null;
    }

    @Override
    public UserStateEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        if (code >= 0 && code <= 5) {
            return UserStateEnum.getUserStateByCode(code);
        }
        return null;
    }

    @Override
    public UserStateEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        if (code >= 0 && code <= 5) {
            return UserStateEnum.getUserStateByCode(code);
        }
        return null;
    }
}