package com.example.ljudio.api;

import com.example.ljudio.model.AlbumImage;
import com.example.ljudio.service.AlbumImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/album/albumImage")
public class AlbumImageController {

    @Autowired
    private AlbumImageService albumImageService;

    @PostMapping
    public AlbumImage addAlbumImage(AlbumImage newAlbumImage) {
        return albumImageService.add(newAlbumImage);
    }

    @GetMapping("/{id}")
    public AlbumImage getAlbumImageById(long id) {
        return albumImageService.getAlbumImageById(id);

    }
}
