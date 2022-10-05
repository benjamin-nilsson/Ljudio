package com.example.ljudio.service;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.model.Song;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@NoArgsConstructor
public class MediaService {

    private  SongService songService;
    private  PlaylistService playlistService;
    private Song currentSong;
    private int currentSongIndex;
    private Song nextSong;
    private Song previousSong;

    public Song song(long songId, long playlistId) {
        currentSong = songService.getSongByID(songId);
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        List<Song> playlistSongs = playlist.getSongs();
        currentSongIndex = playlistSongs.indexOf(currentSong);
        nextSong = playlistSongs.get(currentSongIndex + 1);
        previousSong = playlistSongs.get(currentSongIndex - 1);

        return currentSong;
    }

    public Song next() {
        return nextSong;
    }

    public Song previous() {
        return previousSong;
    }
}
