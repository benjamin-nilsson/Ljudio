package com.example.ljudio.api;

import com.example.ljudio.model.Album;
import com.example.ljudio.model.Song;
import com.example.ljudio.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/spotify")
public class SearchController {

    @Autowired
    private final SearchService searchService;

    @GetMapping("/{title}/{artist}")
    public Map<String, Map<String, String>> search(@PathVariable("title") String title, @PathVariable("artist") String artist) {
        return searchService.search(title, artist);
    }

    @GetMapping("/{songAPIId}")
    public Song track(@PathVariable("songAPIId") String spotifyId) {
        return searchService.track(spotifyId);
    }


}
