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
    private String token = "BQDj32mUUbeV4Xubg4AVWu-MNr5HNAaWlkdYvaP241pJlZpBJ10IiHN-2LTM9Y_1tXe3pEf9OFB_yGK6Kp55ZGR9T3pqM6t7n2Y0Kh3QU8OiL87Jd4jD-Nt4F_7wJYxj77nxJwZqsTQS7gStI635mG2K7UnEENaxdDZafUcPJFW3zCdN52IzmYQ";
    public SearchService(SongService songService, ArtistService artistService, AlbumService albumService) {
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
    }


    public List<Map<String, Map<String, String>>> search(String title, String artist) {
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
    private List<Map<String, Map<String, String>>> parseSearch(String responseBody) {
        List<Map<String, Map<String, String>>> searchResult = new ArrayList<>();
        JSONObject song = new JSONObject(responseBody);
        JSONArray songArray = song.getJSONObject("tracks").getJSONArray("items");

        try {
            int firstObject = 0;
            int mostPopularSongs = 5;
            for (int i = 0; i < mostPopularSongs; i++) {
                Map<String, Map<String, String>> songTrack = new HashMap<>();
                JSONObject songItem = songArray.getJSONObject(i);
                String id = songItem.getString("id");

                JSONObject artistItem = songArray.getJSONObject(i).getJSONObject("album");
                JSONObject artistsName = artistItem.getJSONArray("artists").getJSONObject(firstObject);
                JSONObject albumImage = artistItem.getJSONArray("images").getJSONObject(firstObject);

                Map<String, String> songSpecs = new HashMap<>();

                songSpecs.put("id", id);
                songSpecs.put("songName", songItem.getString("name"));
                songSpecs.put("image", albumImage.getString("url"));
                songSpecs.put("artistName", artistsName.getString("name"));

                songTrack.put("track", songSpecs);

                searchResult.add(songTrack);
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
            String title = song.getString("name");

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

    public Album album(String albumApiId) {
        Album maybeAlbum = albumService.findBySpotifyId(albumApiId);
        System.out.println(maybeAlbum);
        if (maybeAlbum != null)
            return maybeAlbum;

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        HttpsURLConnection connection = null;
        try {
            URL url = new URL("https://api.spotify.com/v1/albums/" + albumApiId);
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

        return parseAlbum(responseContent.toString(), albumApiId);
    }

    private Album parseAlbum(String responseBody, String albumAPIId) {

        JSONObject album = new JSONObject(responseBody);

        try {
            String name = album.getString("name");
            JSONArray songItem = album.getJSONArray("artists");
            String artistId = songItem.getJSONObject(0).getString("id");
            int totalTracks = album.getInt("total_tracks");
            String image = album.getJSONArray("images").getJSONObject(0).getString("url");

            return new Album(albumAPIId, name, totalTracks, image, artist(artistId), new ArrayList<>());

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public List<Album> albums(String artistId) {
        List<Album> maybeAlbums = artistService.getAlbumsByArtist(artistId);
        System.out.println(maybeAlbums);
        if (maybeAlbums != null)
            return maybeAlbums;

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        HttpsURLConnection connection = null;
        try {
            URL url = new URL("https://api.spotify.com/v1/artjsts/" + artistId + "/albums");
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

        return parseAlbum(responseContent.toString(), artistId);
    }

    private List<Album> parseAlbum(String responseBody, String artistId) {
        List<Album> listOfAlbums = new ArrayList<>();

        JSONObject albums = new JSONObject(responseBody);

        try {
            JSONArray songItem = albums.getJSONArray("items");
            String name = songItem.getJSONObject(0).getString("name");
            String albumId = songItem.getJSONObject(0).getString("id");
            int totalTracks = Integer.parseInt(songItem.getJSONObject(0).getString("total_tracks"));
            String image = songItem.getJSONObject(2).getJSONArray("images").getJSONObject(0).getString("url");

            listOfAlbums.add(new Album(albumId, name, totalTracks, image, artistService.getArtistBySpotifyId(artistId), new ArrayList<>()));

            return listOfAlbums;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
