package com.example.bookstore219J05.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;


import com.example.bookstore219J05.model.User;
import org.springframework.jdbc.core.RowMapper;



public class UserMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub

        User user=new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getInt("role"));
        return user;

    }


}

