package com.finnflyer.communitystart.controller;

import com.finnflyer.communitystart.Model.User;
import com.finnflyer.communitystart.dto.AccessTokenDTO;
import com.finnflyer.communitystart.dto.GiteeUser;
import com.finnflyer.communitystart.mapper.UserMapper;
import com.finnflyer.communitystart.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GiteeProvider giteeProvider;

    @Value("${Gitee.client.id}")
    private String client_id;

    @Value("${Gitee.client.secret}")
    private  String client_secret;

    @Value("${Gitee.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                                        HttpServletRequest request
                                         ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setGrant_type("authorization_code");
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        if (giteeUser != null) {
            //login successfully  write cookie & session
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(giteeUser.getName());
            user.setAccount_id(String.valueOf(giteeUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            request.getSession().setAttribute("giteeUser",giteeUser);
            return "redirect:/";

        }else {
            //Relogin
            return "redirect:/" ;
        }

    }
}
