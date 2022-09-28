package com.example.ljudio.service;

import com.example.ljudio.dao.PlaylistDAO;
import com.example.ljudio.dao.SongDAO;
import com.example.ljudio.model.Playlist;
import com.example.ljudio.model.Song;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistService {

    PlaylistDAO playlistDAO;
    SongDAO songDAO;

    public Iterable<Playlist> getAllPlaylist() {
        return playlistDAO.getAllPlaylist();
    }

    public Playlist addPlaylist(Playlist playlist) {
        return playlistDAO.addPlaylist(playlist);
    }

    public void deletePlaylistById(Long id) {
        playlistDAO.deletePlaylistById(id);
    }

    public Playlist getPlaylistById(Long id){
        Optional<Playlist> mightGetPlaylist = playlistDAO.getPlaylistById(id);
        return mightGetPlaylist.orElse(null);
    }

    public Song getSongIdFromPlaylist(Long playlistId,String songName) {
        Optional<Song> maybeSong = getPlaylistById(playlistId).getSongs().stream().filter(song -> song.getTitle().equalsIgnoreCase(songName)).findFirst();
        return maybeSong.orElse(null);
    }

    public void DeleteSongFromPlaylist(Long playlistId, String songName) {
        long songIdFromPlaylist = getSongIdFromPlaylist(playlistId, songName).getSong_id();
        songDAO.deleteSongById(songIdFromPlaylist);
    }

    public Song addSongToPlayList(Long playlistId, String songName){
        Song songIdFromPlaylist = getSongIdFromPlaylist(playlistId, songName);
        return songDAO.save(songIdFromPlaylist);
    }

}
