package com.example.bookstore219J05.controller;


import java.util.HashMap;
import java.util.Map;


import com.example.bookstore219J05.dao.UserDao;
import com.example.bookstore219J05.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/home")
    public String homepage() {

        return "/home";
    }
    @RequestMapping("/login")
    public String login() {

        return "/login";
    }

    @RequestMapping("/register")
    public String register() {

        return "/register";
    }
    @RequestMapping("/checkname")
    @ResponseBody
    public Map<String,String> checkname(String un) {
        Map<String,String> r=new HashMap<String,String>();
        //retrieve the database to check un is ok or not
        if(userDao.checkUserName(un))
            r.put("available", "ok");
        else
            r.put("available", "no");
        //System.out.println("test checkname");
        return r;
    }

    @RequestMapping("/deallogin")
    public String deallogin(User user) {
        int role=userDao.checkUser(user);
        if(role==0)
            return "/admin/index";
        else if(role==1)
            return "/user/index";
        else
            return "/erro";
    }

    @RequestMapping("/adduser")
    public String adduser(User user) {
        userDao.addUser(user);
        String path = "redirect:/home";
        return path;
    }
}

