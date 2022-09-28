package com.example.ljudio.api;

import com.example.ljudio.model.Artist;
import com.example.ljudio.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
@AllArgsConstructor
public class ArtistController {

    @Autowired
    private final ArtistService artistService;

    @PostMapping
    public Artist addArtist(@RequestBody Artist newArtist) {
        return artistService.addArtist(newArtist);
    }

    @PutMapping("/{artistId}/song/{id}")
    public Artist addArtistToSong(@PathVariable("artistId") long artistId,
                              @PathVariable("id") long id) {
        return artistService.addArtistToSong(artistId, id);
    }

    @GetMapping("/{songId}")
    public Artist getArtistById(@PathVariable("artistId") long artistId) {
        return artistService.getArtistByID(artistId);
    }

    @DeleteMapping("/{artistId}")
    public void deleteArtistById(@PathVariable("artistId") long artistId) {
        artistService.deleteArtistById(artistId);
    }

    @GetMapping("/{spotifyId}")
    public Artist getArtistBySpotifyId(@PathVariable("spotifyId") long spotifyId) {
        return artistService.getArtistBySpotifyId(spotifyId);
    }

    @GetMapping("/{name}")
    public Artist getArtistByName(@PathVariable("name") String name) {
        return artistService.getArtistByName(name);
    }

    @GetMapping("/{album}")
    public Artist getAlbumByArtist(@PathVariable("album") String album) {
        return artistService.getAlbumByArtist(album);
    }
}
