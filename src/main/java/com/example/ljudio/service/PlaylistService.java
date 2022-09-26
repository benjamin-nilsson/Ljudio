package com.example.ljudio.service;

import com.example.ljudio.dao.PlaylistDAO;
import com.example.ljudio.model.Playlist;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService {

    PlaylistDAO playlistDAO;

    public PlaylistService(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }


    public Iterable<Playlist> getAllPlaylist() {
        return playlistDAO.getAllPlaylist();
    }

    public Playlist addPlaylist(Playlist playlist) {
        return playlistDAO.addPlaylist(playlist);
    }

    public void deletePlaylistById(Integer id) {
        playlistDAO.deletePlaylistById(id);
    }

    public Playlist getPlaylistById(Integer id){
        Optional<Playlist> mightGetPlaylist = playlistDAO.getPlaylistById(id);
        return mightGetPlaylist.orElse(null);
    }
}
