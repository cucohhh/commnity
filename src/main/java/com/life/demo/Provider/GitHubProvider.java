package com.life.demo.Provider;

import com.alibaba.fastjson.JSON;
import com.life.demo.Dto.AccessTokenDTO;
import com.life.demo.Dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;



@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){

         MediaType mediaType =MediaType.get("application/json; charset=utf-8");

         OkHttpClient client = new OkHttpClient();


         RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO),mediaType);
         Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
         try (Response response = client.newCall(request).execute()) {
                String string=response.body().string();
                String[] split = string.split("&");
                String[] split2 = split[0].split("=");
                String accessToken = split2[1];
                System.out.println(string);
                System.out.println(accessToken);
                return accessToken;
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;


    }


    public GithubUser getGitHubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
