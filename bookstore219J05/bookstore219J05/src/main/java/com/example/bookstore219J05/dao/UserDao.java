package com.example.bookstore219J05.dao;


import java.util.List;


import com.example.bookstore219J05.mapper.UserMapper;
import com.example.bookstore219J05.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    public  UserDao(){
        DriverManagerDataSource dataSource=new  DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/bookstore?serverTimezone=GMT");
        dataSource.setUsername("root");
        dataSource.setPassword("123456789");
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public int checkUser(User user) {
        String sql="select * from user where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'";
        List<User> users=jdbcTemplate.query(sql,new UserMapper());
        if(user==null|| users.isEmpty())
            return 2;//user is not in database
        else
        if((users.get(0)).getRole()==1)
            return 1;//user is an ordinary user
        else
            return 0;// user is an adminstrator;
    }
    public boolean checkUserName(String username) {
        String sql="select * from user where username='"+username+"'";
        List<User> users=jdbcTemplate.query(sql,new UserMapper());
        return users.isEmpty();//empty means username is available.
    }

    public int addUser(User user) {
        String sql = "insert into user (id, username, password, role) values (null,?,?,1)";
        Object[] params = {user.getUsername(), user.getPassword()};
        return jdbcTemplate.update(sql, params);
    }

}

