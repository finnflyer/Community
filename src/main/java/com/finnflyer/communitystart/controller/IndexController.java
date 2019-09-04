package com.finnflyer.communitystart.controller;

import com.finnflyer.communitystart.Model.User;
import com.finnflyer.communitystart.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null) return"index";
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user !=null)
                    request.getSession().setAttribute("giteeUser",user);
                break;
            }
        }
        return "index";
    }
}
