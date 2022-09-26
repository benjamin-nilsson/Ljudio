package com.example.ljudio.api;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.service.PlaylistService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080/")
@RequestMapping("/api/playlist")
public class PlaylistController {

    PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public Iterable<Playlist> getAllPlaylist() {
        return playlistService.getAllPlaylist();
    }

    @PostMapping
    public Playlist addPlaylist(@RequestBody Playlist playlist) {
        return playlistService.addPlaylist(playlist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylistById(@PathVariable("id") Integer id) {
        playlistService.deletePlaylistById(id);
    }

    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable("id") Integer id) {
        return playlistService.getPlaylistById(id);
    }

}
