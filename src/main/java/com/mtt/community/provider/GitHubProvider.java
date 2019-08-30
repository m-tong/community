package com.mtt.community.provider;

import com.alibaba.fastjson.JSON;
import com.mtt.community.entity.AccessTokenEntity;
import com.mtt.community.entity.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class GitHubProvider {

    public String getAccrssToken(AccessTokenEntity accessTokenEntity) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenEntity));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return string;
        } catch (IOException e) {
            return null;

        }
    }

    public GitHubUser gitUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user"+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string,GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            return null;
    }
    }

}
