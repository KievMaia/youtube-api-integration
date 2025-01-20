package com.youtube.api.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.youtube.api.auth.Auth;
import com.youtube.api.service.YoutubeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class OAuthController {

    @GetMapping("/authorize")
    public RedirectView authorize() {
        String viewUrl = Auth.getAuthorizationUrl();
        return new RedirectView(viewUrl);
    }

    @GetMapping("/Callback")
    public String callback(@RequestParam("code") String code) {
        var tokenResponse = Auth.exchangeCode(code);
        var credential = Auth.getCredential(tokenResponse);
        var description = YoutubeService.getYoutubeChannelName(credential);
        return "Esta é a descrição do meu canal! %s".formatted(description);
    }
}
