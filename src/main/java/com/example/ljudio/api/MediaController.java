package com.example.ljudio.api;

import com.example.ljudio.model.Song;
import com.example.ljudio.service.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/mediaplayer")
public class MediaController {

    @Autowired
    private final MediaService mediaService;

    @GetMapping("/{song}/{playlistId}")
    public Song getSong(@PathVariable("song") long songId,@PathVariable("playlistId") long playlistId) {
        return mediaService.song(songId, playlistId);
    }

    @PutMapping("/{song}/{playlistId}")
    public Song nextSong() {return mediaService.next();}

    @PutMapping("/{song}/{playlistId}")
    public Song prevSong() {return mediaService.previous();}
}
