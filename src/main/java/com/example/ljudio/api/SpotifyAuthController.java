package com.example.ljudio.api;

import com.example.ljudio.spotify.SpotifyConnect;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RequestMapping("api/spotify-auth")
@RestController
public class SpotifyAuthController  {

    @Value("spotify.frontend.url")
    String FRONTEND_URL;

    @Autowired
    private SpotifyConnect spotifyConnect;

    @GetMapping
    public RedirectView handleAuthCode(@RequestParam String code) throws ParseException, SpotifyWebApiException, IOException {
        spotifyConnect.addAuthCode(code);

        return new RedirectView(FRONTEND_URL);
    }
}
