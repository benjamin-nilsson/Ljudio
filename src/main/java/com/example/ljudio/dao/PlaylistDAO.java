package com.example.ljudio.dao;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.repository.PlaylistRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlaylistDAO {

    PlaylistRepository playlistRepository;

    public PlaylistDAO(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Iterable<Playlist> getAllPlaylist(){
        return playlistRepository.findAll();
    }

    public Playlist addPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void deletePlaylistById(Integer id) {
        playlistRepository.deleteById(id);
    }

    public Optional<Playlist> getPlaylistById(Integer id) {
        return playlistRepository.findById(id);
    }
}
