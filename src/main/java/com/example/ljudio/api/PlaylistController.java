package com.example.ljudio.api;

import com.example.ljudio.model.Playlist;
import com.example.ljudio.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080/")
@RequestMapping("/api/playlist")
public class PlaylistController {

    PlaylistService playlistService;

    @GetMapping
    public Iterable<Playlist> getAllPlaylist() {
        return playlistService.getAllPlaylist();
    }

    @PostMapping
    public Playlist addPlaylist(@RequestBody Playlist playlist) {
        return playlistService.addPlaylist(playlist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylistById(@PathVariable("id") Long id) {
        playlistService.deletePlaylistById(id);
    }

    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable("id") Long id) {
        return playlistService.getPlaylistById(id);
    }

}
