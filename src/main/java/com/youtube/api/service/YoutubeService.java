package com.youtube.api.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;

public class YoutubeService {
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static String getYoutubeChannelName(Credential credential) {
        try {
            var youTube = new YouTube.Builder(
                    HTTP_TRANSPORT,
                    JSON_FACTORY,
                    credential
            ).setApplicationName("YouTube API Application")
                    .build();

            var channelList = youTube.channels().list("snippet");
            channelList.setMine(Boolean.TRUE);

            var listResponse = channelList.execute();

            if (listResponse.getItems().isEmpty()) {
                return "Unknown channel";
            }
            return listResponse.getItems().get(0).getSnippet().getDescription();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
