package com.example.ljudio.dao;

import com.example.ljudio.model.Song;
import com.example.ljudio.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SongDAO {

    private final SongRepository songRepository;

    public Song save(Song newSong) {
        return songRepository.save(newSong);
    }

    public Iterable<Song> findAllSongs() {
        return songRepository.findAll();
    }

    public void deleteSongFromAUsersList(long songId) {
        songRepository.deleteById(songId);
    }

    public Optional<Song> findSongById(long songId) {
        return songRepository.findById(songId);
    }

    public void deleteSongById(long song_id) {
        songRepository.deleteById(song_id);
    }

    public Optional<Song> findSongBySpotifyId(long spotifyId) {
        return songRepository.findById(spotifyId);
    }

    public Optional<Song> findSongByTitle(String title) {
        return songRepository.findById(Long.valueOf(title));
    }

    public Optional<Song> findSongByAlbum(String album) {
        return songRepository.findById(Long.valueOf(album));
    }
}
