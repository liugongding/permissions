package com.dingding.permissions.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liudingding
 * @ClassName PerController
 * @description
 * @date 2020/6/25 6:36 下午
 */

@Controller
@Slf4j
public class PerController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //执行登录
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            log.error(e.getMessage());
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            log.error(e.getMessage());
            return "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String Unauthorized(){
        return "未经授权，无法访问此页面";
    }
}
