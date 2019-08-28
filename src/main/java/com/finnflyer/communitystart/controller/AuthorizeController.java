package com.finnflyer.communitystart.controller;

import com.finnflyer.communitystart.dto.AccessTokenDTO;
import com.finnflyer.communitystart.dto.GiteeUser;
import com.finnflyer.communitystart.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code
                                         ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setGrant_type("authorization_code");
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        GiteeUser user = giteeProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "redirect:/";
    }
}
