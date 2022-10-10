package com.example.ljudio.api;

import com.example.ljudio.model.Song;
import com.example.ljudio.service.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
