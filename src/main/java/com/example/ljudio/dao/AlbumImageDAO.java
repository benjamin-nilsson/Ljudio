package com.example.ljudio.dao;

import com.example.ljudio.model.AlbumImage;
import com.example.ljudio.repository.AlbumImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class AlbumImageDAO {

    private AlbumImageRepository albumImageRepository;

    public AlbumImage save(AlbumImage newAlbumImage) {
        return albumImageRepository.save(newAlbumImage);
    }

    public Optional<AlbumImage> findById(long id) {
        return albumImageRepository.findById(id);
    }
}
