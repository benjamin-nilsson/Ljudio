package com.example.ljudio.service;

import com.example.ljudio.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private SongService songService;
    private ArtistService artistService;
    private AlbumService albumService;
    private String token = "BQDecZUAvog-2c-1HsV8TVIiG5ddkdMl-sHWnkKokBAVnEE8c-7wnwiFSYewbLush8ATLPtOZgxF0z-GHe8qloHhSuPZAywvU5-vUo-iC2kJGK10mcJxEbkdQsBGYS7tsZAZtoNX2Ru4k4_nDNDht7GlsJrW1VaUqSDBXgfJHVC7J_5gHyYvaIU";
    public SearchService(SongService songService, ArtistService artistService, AlbumService albumService) {
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
    }


    public Map<String, Map<String, String>> search(String title, String artist) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        var songTitle = URLEncoder.encode(title, StandardCharsets.UTF_8).replace("+", "%20");
        var artistTitle = URLEncoder.encode(artist, StandardCharsets.UTF_8).replace("+", "%20");

        System.out.println(songTitle);
        HttpsURLConnection connection = null;
        try {
            // Http header fields.
            URL url = new URL("https://api.spotify.com/v1/search?query=" + songTitle + "%20" + artistTitle + "&type=track%2Cartist");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            System.out.println(connection.toString());
            System.out.println(url);
            int status = connection.getResponseCode();
            System.out.println(status);
            if (status > 200)
                return null;
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null)
                    responseContent.append(line);
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        return parseSearch(responseContent.toString());
    }
    private Map<String, Map<String, String>> parseSearch(String responseBody) {
        Map<String, Map<String, String>> searchResult = new HashMap<>();
        JSONObject song = new JSONObject(responseBody);
        JSONArray songArray = song.getJSONObject("tracks").getJSONArray("items");

        try {
            int firstObject = 0;
            int mostPopularSongs = 5;
            for (int i = 0; i < mostPopularSongs; i++) {
                JSONObject songItem = songArray.getJSONObject(i);
                String id = songItem.getString("id");

                JSONObject artistItem = songArray.getJSONObject(i).getJSONObject("album");
                JSONObject artistsName = artistItem.getJSONArray("artists").getJSONObject(firstObject);
                JSONObject albumImage = artistItem.getJSONArray("images").getJSONObject(firstObject);

                Map<String, String> songSpecs = new HashMap<>();

                songSpecs.put("songName", songItem.getString("name"));
                songSpecs.put("image", albumImage.getString("url"));
                songSpecs.put("artistName", artistsName.getString("name"));

                searchResult.put(id, songSpecs);
            }

            return searchResult;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Song track(String spotifyId) {
        Song maybeSong = songService.findBySpotifyId(spotifyId);
        if (maybeSong != null)
            return maybeSong;

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        var id = URLEncoder.encode(spotifyId, StandardCharsets.UTF_8).replace("+", "%20");

        System.out.println(id);
        HttpsURLConnection connection = null;
        try {
            URL url = new URL("https://api.spotify.com/v1/tracks/" + id);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            System.out.println(connection.toString());
            System.out.println(url);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status > 200)
                return null;
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null)
                    responseContent.append(line);
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        return parseTrack(responseContent.toString(), spotifyId);
    }

    private Song parseTrack(String responseBody, String spotifyId) {

        Map<String, Map<String, String>> searchResult = new HashMap<>();
        JSONObject song = new JSONObject(responseBody);
        JSONObject songItem = song.getJSONObject("album");

        try {
            int firstObject = 0;
            String artistName = songItem.getJSONArray("artists").getJSONObject(0).getString("name");
            String artistId = songItem.getJSONArray("artists").getJSONObject(0).getString("id");
            Artist maybeArtist = artist(artistId);

            String releaseDate = songItem.getString("release_date");
            String albumId = songItem.getString("id");
            String title = songItem.getString("name");

            return new Song(spotifyId, title, albumId, releaseDate, new ArrayList<>(List.of(maybeArtist)));

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Artist artist(String artistId) {
        Artist maybeArtist = artistService.getArtistBySpotifyId(artistId);
        System.out.println(maybeArtist);
        if (maybeArtist != null)
            return maybeArtist;

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        HttpsURLConnection connection = null;
        try {
            URL url = new URL("https://api.spotify.com/v1/artists/" + artistId);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            System.out.println(connection.toString());
            System.out.println(url);

            int status = connection.getResponseCode();
            System.out.println(status);
            if (status > 200)
                return null;
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null)
                    responseContent.append(line);
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        return parseArtist(responseContent.toString(), artistId);
    }

    private Artist parseArtist(String responseBody, String artistId) {
        JSONObject artist = new JSONObject(responseBody);

        try {
            String name = artist.getString("name");

            return new Artist(artistId, name, new ArrayList<>());

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
