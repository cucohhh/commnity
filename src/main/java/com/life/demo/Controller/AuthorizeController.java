package com.life.demo.Controller;

import com.life.demo.Dto.AccessTokenDTO;
import com.life.demo.Dto.GithubUser;
import com.life.demo.Provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider ;
    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken  = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = gitHubProvider.getGitHubUser(accessToken);
        if(user !=null){
            //登陆成功 操作
            //写入session
            request.getSession().setAttribute("user" ,user);
            return "redirect:/";
        }else{
            //登陆失败操作
            return "redirect:/";
        }



    }
}
