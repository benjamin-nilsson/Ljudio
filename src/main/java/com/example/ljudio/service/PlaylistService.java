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

    public List<Playlist> deleteUserPlaylist(Long id, long userId){
        User user = userDAO.findByID(userId).get();
        Playlist playlistFromUser = user.getPlaylist().stream()
                .filter(playlist -> playlist.getId() == id)
                .findFirst()
                .orElse(null);
        List<Playlist> playlists = user.getPlaylist();

        playlists.remove(playlistFromUser);
        user.setPlaylist(playlists);

        return userDAO.save(user).getPlaylist();
    }

    public Song getSongIdFromPlaylist(Long playlistId,String songName) {
        Optional<Song> maybeSong = getPlaylistById(playlistId)
                .getSongs()
                .stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(songName))
                .findFirst();
        return maybeSong.orElse(null);
    }

    public Playlist DeleteSongFromPlaylist(Long playlistId, long songId) {
        Song song = songDAO.findSongById(songId).get();
        List<Song> playListSongs = getSongsFromPlaylist(playlistId);
        Playlist playlist = getPlaylistById(playlistId);

        playListSongs.remove(song);
        playlist.setSongs(playListSongs);

        return playlistDAO.addPlaylist(playlist);
    }

    public Playlist addSongToPlayList(Long playlistId, long songId){

        Song song = songDAO.findSongById(songId).get();
        List<Song> playlistSongs = getSongsFromPlaylist(playlistId);
        Playlist playlist = getPlaylistById(playlistId);

        if (playlistSongs.contains(song)) {
            return playlist;
        }

        playlistSongs.add(song);
        playlist.setSongs(playlistSongs);

        return playlistDAO.addPlaylist(playlist);
    }

    private List<Song> getSongsFromPlaylist(long id) {
        Optional<Playlist> maybePlaylist = playlistDAO.getPlaylistById(id);

        Playlist playlist = maybePlaylist.get();
        List<Song> playlistSongs = playlist.getSongs();
        return  playlistSongs;
    }
}
