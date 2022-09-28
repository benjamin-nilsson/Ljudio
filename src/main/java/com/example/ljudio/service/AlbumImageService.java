package com.example.ljudio.service;

import com.example.ljudio.dao.AlbumImageDAO;
import com.example.ljudio.model.AlbumImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AlbumImageService {

    private AlbumImageDAO albumImageDAO;

    public AlbumImage add(AlbumImage newAlbumImage) {
        return albumImageDAO.save(newAlbumImage);
    }

    public AlbumImage getAlbumImageById(long id) {
        return albumImageDAO.findById(id).orElse(null);
    }
}
