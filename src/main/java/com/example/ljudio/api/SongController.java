package com.example.ljudio.api;

import com.example.ljudio.model.Song;
import com.example.ljudio.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/song")
@AllArgsConstructor
public class SongController {

    @Autowired
    private final SongService songService;

    @PostMapping
    public Song addSong(@RequestBody Song newSong) {
        return songService.addSong(newSong);
    }

   /*
    @PutMapping("/{songId}/user/{id}")
    public Song addUserToSong(@PathVariable("songId") long songId,
                                   @PathVariable("id") long userId) {
        return songService.addUserToSong(songId, userId);
    }

    */

    @GetMapping("/{songId}")
    public Song getSongById(@PathVariable("songId") long songId) {
        return songService.getSongByID(songId);
    }

    @DeleteMapping("/{songId}")
    public void deleteSongById(@PathVariable("songId") long songId) {
        songService.deleteSongById(songId);
    }

    @GetMapping("/spotify/{spotifyId}")
    public Song getSongBySpotifyId(@PathVariable("spotifyId") String spotifyId) {
        return songService.findBySpotifyId(spotifyId);
    }


}
