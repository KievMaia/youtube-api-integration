package com.youtube.api.auth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

public class Auth {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final Collection<String> SCOPES = List.of(
            "https://www.googleapis.com/auth/youtube.readonly"
    );

    public static TokenResponse exchangeCode(String code) {
        try {
            var googleClientSecrets = getGoogleClientSecrets();
            var codeFlow = buildGoogleAuthorizationCodeFlow(googleClientSecrets);
            return codeFlow.newTokenRequest(code)
                   .setRedirectUri(googleClientSecrets.getDetails()
                           .getRedirectUris()
                           .get(0))
                   .execute();
        } catch (IOException e) {
            throw new RuntimeException("Error when trying to load google client");
        }
    }

    public static Credential getCredential(TokenResponse tokenResponse) {
        try {
            var googleClientSecrets = getGoogleClientSecrets();
            var codeFlow = buildGoogleAuthorizationCodeFlow(googleClientSecrets);
            return codeFlow.createAndStoreCredential(tokenResponse, "userId");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAuthorizationUrl() {
        try {
            var googleClientSecrets = getGoogleClientSecrets();
            var codeFlow = buildGoogleAuthorizationCodeFlow(googleClientSecrets);
            return codeFlow.newAuthorizationUrl()
                    .setRedirectUri(googleClientSecrets.getDetails()
                            .getRedirectUris()
                            .get(0))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Error when trying to load google client");
        }
    }

    private static GoogleAuthorizationCodeFlow buildGoogleAuthorizationCodeFlow(GoogleClientSecrets googleClientSecrets) {
        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                googleClientSecrets,
                SCOPES
        ).setAccessType("offline").build();
    }

    private static GoogleClientSecrets getGoogleClientSecrets() throws IOException {
        var resourceAsStream = Auth.class.getResourceAsStream("/client_secrets.json");
        return GoogleClientSecrets.load(
                JSON_FACTORY,
                new InputStreamReader(resourceAsStream)
        );
    }
}
