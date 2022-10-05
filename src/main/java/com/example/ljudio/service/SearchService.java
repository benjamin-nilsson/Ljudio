package com.example.ljudio.service;

import lombok.AllArgsConstructor;
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
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class SearchService {

    /**
     * Request Spotify's API for a song/artist.
     *
     * @param title
     * @Returns the songs URI.
     */
    public Map<String, Map<String, String>> search(String title, String artist) {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        // We fix the title and the artist according to Spotify's requirement for querying a song/artist.
        var songTitle = URLEncoder.encode(title, StandardCharsets.UTF_8).replace("+", "%20");
        var artistTitle = URLEncoder.encode(artist, StandardCharsets.UTF_8).replace("+", "%20");

        System.out.println(songTitle);
        // Querying the Spotify's API for finding a song/artist.
        HttpsURLConnection connection = null;
        try {
            // Http header fields.
            URL url = new URL("https://api.spotify.com/v1/search?query=" + songTitle + "%20" + artistTitle + "&type=track%2Cartist");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer BQAEOM0VioHlLez9ATYoBro8yoDtLM7H5m-Nq1Z-KC2DWCWf46EsGoRzGMX5p7XQoewR9S9_4qaj9C_4yzF6juGNYa4Jx9kFux5F4XDNCwz1rxne2-pFURGCD_0uYJLXCrd06CEy2zrWLK3wEWRyomdPhHh8MGRYFnWhBeTgYg_wpJxyC-KvblQ");

            System.out.println(connection.toString());
            System.out.println(url);
            /*
            If we get a bad status due to that the video is not about music
            we want to move on to the next one without throwing an exception.
            If we however get a bad connection due to other reasons, it will
            be caught in the CreateNewPlaylist as this is where we first establish
            a connection.
             */
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

    /**
     * Parse our json data.
     * Goes through the response data from the server.
     *
     * @param responseBody
     * @Returns the songs URI.
     */
    private Map<String, Map<String, String>> parseSearch(String responseBody) {
        Map<String, Map<String, String>> searchResult = new HashMap<>();
        JSONObject song = new JSONObject(responseBody);
        JSONArray songArray = song.getJSONObject("tracks").getJSONArray("items");


        /*
        Need to handle JSONException as all liked videos
        might not be music content or the original song.
         */
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
}
