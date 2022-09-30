package com.example.ljudio.service;

import com.example.ljudio.dao.PlaylistDAO;
import com.example.ljudio.dao.SongDAO;
import com.example.ljudio.dao.UserDAO;
import com.example.ljudio.model.Playlist;
import com.example.ljudio.model.Song;
import com.example.ljudio.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistService {

    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;
    private UserDAO userDAO;

    public Iterable<Playlist> getAllPlaylist() {
        return playlistDAO.getAllPlaylist();
    }

    public Playlist addPlaylist(Playlist playlist) {
        return playlistDAO.addPlaylist(playlist);
    }

    public List<Playlist> addPlaylistToUser(long playlistId, long userId) {
        Optional<Playlist> maybePlaylist = playlistDAO.getPlaylistById(playlistId);
        Optional<User> maybeUser = userDAO.findByID(userId);

        if (maybeUser.isEmpty() || maybePlaylist.isEmpty())
            return null;

        Playlist playlist = maybePlaylist.get();
        User user = maybeUser.get();

        List<Playlist> userPlaylist = user.getPlaylist();
        userPlaylist.add(playlist);

        user.setPlaylist(userPlaylist);
        return userDAO.save(user).getPlaylist();
    }

    public Playlist getPlaylistById(Long id){
        Optional<Playlist> mightGetPlaylist = playlistDAO.getPlaylistById(id);
        return mightGetPlaylist.orElse(null);
    }

    public void deletePlaylistById(Long id) {
        playlistDAO.deletePlaylistById(id);
    }

    public Song getSongIdFromPlaylist(Long playlistId,String songName) {
        Optional<Song> maybeSong = getPlaylistById(playlistId)
                .getSongs()
                .stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(songName))
                .findFirst();
        return maybeSong.orElse(null);
    }

    public void DeleteSongFromPlaylist(Long playlistId, String songName) {
        long songIdFromPlaylist = getSongIdFromPlaylist(playlistId, songName).getSong_id();
        songDAO.deleteSongById(songIdFromPlaylist);
    }
}
