package com.example.ljudio.api;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.model.Song;
import com.example.ljudio.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping
    public Iterable<Playlist> getAllPlaylist() {
        return playlistService.getAllPlaylist();
    }

    @PostMapping
    public Playlist addPlaylist(@RequestBody Playlist playlist) {
        System.out.println("hej");
        return playlistService.addPlaylist(playlist);
    }

    @PutMapping("/{id}/user/{userId}")
    public List<Playlist> addPlaylistToUser(@PathVariable("id") long id, @PathVariable("userId") long userId) {
        System.out.println("bye");
        return playlistService.addPlaylistToUser(id, userId);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylistById(@PathVariable("id") Long id) {
        playlistService.deletePlaylistById(id);
    }

    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable("id") Long id) {
        return playlistService.getPlaylistById(id);
    }

    @GetMapping("/{id}/songs")
    public List<Song> getPlaylistSongs(@PathVariable("id") Long id) {
        return playlistService.getPlaylistSongs(id);
    }

    @PutMapping("/{id}/songAdd/{songId}")
    public Playlist addSongToPlaylist(@PathVariable("id") long id, @PathVariable("songId") String songId) {
        return playlistService.addSongToPlayList(id, songId);
    }

    @PutMapping("/{id}/songDel/{songId}")
    public Playlist addSongToPlayList(@PathVariable("id")long id, @PathVariable("songId") long songId) {
        return playlistService.DeleteSongFromPlaylist(id, songId);
    }

    @PutMapping("/{id}/delPlay/{userId}")
    public List<Playlist> deletePlaylistFromUser(@PathVariable("id")Long id, @PathVariable("userId")long userId) {
        return playlistService.deleteUserPlaylist(id, userId);
    }
}
