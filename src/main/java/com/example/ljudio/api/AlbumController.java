package com.example.ljudio.api;


import com.example.ljudio.model.Album;
import com.example.ljudio.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/album")
@AllArgsConstructor
public class AlbumController {

    @Autowired
    private final AlbumService albumService;

    @PostMapping
    public Album addAlbum(@RequestBody Album newAlbum) {
        return albumService.addAlbum(newAlbum);
    }

    @GetMapping("/{albumId}")
    public Album getAlbumById(@PathVariable("albumId") long albumId) {
        return albumService.getAlbumById(albumId);
    }

    @DeleteMapping("/{albumId}")
    public void deleteAlbumById(@PathVariable("albumId") long albumId) {
        albumService.deleteAlbumById(albumId);
    }

    @GetMapping("/{spotifyId}")
    public Album getAlbumBySpotifyId(@PathVariable("spotifyId") long spotifyId) {
        return albumService.getAlbumBySpotifyId(spotifyId);
    }

    @GetMapping("/{name}")
    public Album getAlbumByName(@PathVariable("name") String name) {
        return albumService.getAlbumByName(name);
    }

    @GetMapping("/{artist}")
    public Album getArtistByAlbum(@PathVariable("artist") String artist) {
        return albumService.getArtistByAlbum(artist);
    }
}
