package com.example.ljudio.dao;

import com.example.ljudio.model.Album;
import com.example.ljudio.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AlbumDAO {

    private final AlbumRepository albumRepository;

    public Album save(Album newAlbum) {
        return albumRepository.save(newAlbum);
    }

    public Iterable<Album> findAllAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> findAlbumById(long albumId) {
        return albumRepository.findById(albumId);
    }

    public void deleteAlbumById(long albumId) {
        albumRepository.deleteById(albumId);
    }

    public Optional<Album> findArtistByName(String name) {
        return albumRepository.findById(Long.valueOf(name));
    }

    public Optional<Album> findArtistByAlbum(String artist) {
        return albumRepository.findById(Long.valueOf(artist));
    }

    public Optional<Album> getAlbumBySpotifyId(long spotifyId) {
        return albumRepository.findById(spotifyId);
    }
}
