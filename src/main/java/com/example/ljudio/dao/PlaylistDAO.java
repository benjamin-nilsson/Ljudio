package com.example.ljudio.dao;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PlaylistDAO {

    PlaylistRepository playlistRepository;

    public Iterable<Playlist> getAllPlaylist(){
        return playlistRepository.findAll();
    }

    public Playlist addPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void deletePlaylistById(Long id) {
        playlistRepository.deleteById(id);
    }

    public Optional<Playlist> getPlaylistById(Long id) {
        return playlistRepository.findById(id);
    }
}
