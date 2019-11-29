package com.example.springweb.controller;

import com.example.springweb.dao.Appcheck;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    private String loginUsrId;

    @RequestMapping("/hello")
    public String hello(){
        logger.info("hello logging" + helloService.getUserList());
        return "hello";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET) // 获取用户输入数据
    public String hello(Model model){
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HelloUser user, Model model, HttpSession session) {
        String userId = user.getId();
        String password = user.getPassword();
        logger.info("login  your id and password is :" + userId + " " + password);
        HelloUser one = helloService.getOne(userId);    // 判断对象是否为空
        if (one.getId() == null) {
            model.addAttribute("loginInfo", "该用户不存在");
            return "login";
        } else if (one.getPassword().equals(password)) {
            logger.info("you've logged!!");
            //String name=one.getName();
            model.addAttribute("loginInfo", "登录成功");
            session.setAttribute("usrName",one.getName());
            session.setAttribute("usrID",userId);
            session.setAttribute("usrPassword",password);
            loginUsrId = userId;
            return "redirect:dashboard";
        } else {
            model.addAttribute("loginInfo", "密码错误");
            return "login";
        }
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET) // 获取用户输入数据
    public String trySignUp(Model model){
        return "signup";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(HelloUser user, Model model, HttpSession session) {
        String userId = user.getId();
        String usrName = user.getName();
        String password = user.getPassword();
        logger.info("try to signup ID "+userId);
        HelloUser one = helloService.getOne(userId);    // 判断对象是否为空
        if (one.getId() != null) {
            model.addAttribute("signupInfo", "该用户已存在");
            return "signup";
        } else {
            logger.info("sign up new id");
            model.addAttribute("signupInfo", "注册成功");
            Map<String, String> newinfo = new HashMap<String, String>();
            newinfo.put("id",userId);
            newinfo.put("name",usrName);
            newinfo.put("password",password);
            helloService.InsertUser(newinfo);
            loginUsrId = userId;
            session.setAttribute("usrName",usrName);
            session.setAttribute("usrID",userId);
            session.setAttribute("usrPassword",password);
            loginUsrId = userId;
            return "redirect:dashboard";
        }
    }

    @RequestMapping(value = "/usrinfo", method = RequestMethod.GET)
    public String usrinfo(){
        if(loginUsrId==null)
            return "redirect:login";
        logger.info("change usrinfo");
        return "usrinfo";
    }
    @RequestMapping(value = "/usrinfo", method = RequestMethod.POST)
    public String changeinfo(HelloUser user, Model model, HttpSession session){
        logger.info("change usrinfo");
        HelloUser one = helloService.getOne(loginUsrId);
        String userName = user.getName();
        String password = user.getPassword();
        Map<String, String> newinfo = new HashMap<String, String>();
        newinfo.put("id",loginUsrId);
        if(userName!=null){
            newinfo.put("name",userName);
            session.setAttribute("usrName",userName);
        } else {
            newinfo.put("name",one.getName());
        }
        if(password!=null){
            newinfo.put("password",password);
            session.setAttribute("usrPassword",password);
        }else {
            newinfo.put("password",one.getPassword());
        }
        helloService.UpdateByID(newinfo);
        return "redirect:dashboard";
    }

    @RequestMapping("/logout")
    public String logout(HelloUser user, Model model, HttpSession session){
        logger.info("log out now");
        session.setAttribute("usrID",null);
        session.setAttribute("usrName",null);
        return "redirect:login";
    }
}
