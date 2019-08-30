package com.mtt.community.controller;

import com.mtt.community.entity.AccessTokenEntity;
import com.mtt.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizingController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id("9f5b061b916b908b4d2e");
        accessTokenEntity.setClient_secret("7c151fcae500a77ee870d742359d7ee4ebdbc58f");
        accessTokenEntity.setCode(code);
        accessTokenEntity.setRedirect_uri("http://localhost:12/callback");
        accessTokenEntity.setState(state);
        gitHubProvider.getAccrssToken(accessTokenEntity);
        return "index";
    }
}
