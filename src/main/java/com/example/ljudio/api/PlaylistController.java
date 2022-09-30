package com.example.ljudio.api;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.model.User;
import com.example.ljudio.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080/")
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
        return playlistService.addPlaylist(playlist);
    }

    @PutMapping("/{id}/user/{userId}")
    public List<Playlist> addPlaylistToUser(@PathVariable("id") long id, @PathVariable("userId") long userId) {
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

    @PutMapping("/{id}/song/{songId}")
    public Playlist addSongToPlaylist(@PathVariable("id") long id, @PathVariable("songId") long songId) {
        return playlistService.addSongToPlayList(id, songId);
    }
}
