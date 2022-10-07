package com.example.ljudio.service;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.model.Song;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
@NoArgsConstructor
public class MediaService {

    private SongService songService;
    private PlaylistService playlistService;
    private Song current;
    private int currentIndex;
    private Song next;
    private Song previous;


    public Song song(long songId, long playlistId) {
        current = songService.getSongByID(songId);
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        List<Song> playlistSongs = playlist.getSongs();
        currentIndex = playlistSongs.indexOf(current);
        next = playlistSongs.get(currentIndex + 1);
        previous = playlistSongs.get(currentIndex - 1);

        return current;
    }

    public Song next() {
        return next;
    }

    public Song previous() {
        return previous;
    }
}
