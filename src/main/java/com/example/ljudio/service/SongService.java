package com.example.ljudio.service;

import com.example.ljudio.dao.SongDAO;
import com.example.ljudio.model.Song;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {

    private final SongDAO songDAO;
    //private final UserDAO userDAO;

    public Song addSong(Song newSong) {
        Song song = findBySpotifyId(newSong.getSpotify_id());

        if (song != null) {
            return song;
        }
        return songDAO.save(newSong);
    }

    /*public Song addUserToSong(long songId, long userId) {
        Optional<Song> maybeSong = songDAO.findSongById(songId);
        Optional<User> maybeUser = userDAO.findUserById(userId);
        if (maybeUser.isEmpty() || maybeSong.isEmpty()) {
            return null;
        }

        User user = maybeUser.get();
        Song song = maybeSong.get();

        List<User> users = song.getUser_list();
        users.add(user);
        song.setUser_list(users);
        return songDAO.save(song);
    }

     */

    private Song findBySpotifyId(String spotifyId) {
        return getAllSongs().stream()
                .filter(song -> song.getSpotify_id().equalsIgnoreCase(spotifyId))
                .findFirst()
                .orElse(null);
    }

    private List<Song> getAllSongs() {
        return (List<Song>) songDAO.findAllSongs();
    }

    public Song getSongByID(long songId) {
        return songDAO.findSongById(songId).orElse(null);
    }

    public void deleteSongById(long songId) {
        Optional<Song> maybeSong = songDAO.findSongById(songId);
        if (maybeSong.isEmpty())
            return;
        Song song = maybeSong.get();

        songDAO.deleteSongById(song.getSong_id());
    }

    public Song getSongBySpotifyId(long spotifyId) {
        return songDAO.findSongBySpotifyId(spotifyId).orElse(null);
    }

    public Song getSongByTitle(String title) {
        return songDAO.findSongByTitle(title).orElse(null);
    }

    public Song getSongByAlbum(String album) {
        return songDAO.findSongByAlbum(album).orElse(null);
    }
}
