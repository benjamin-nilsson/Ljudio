package com.example.ljudio.service;

import com.example.ljudio.dao.AlbumDAO;
import com.example.ljudio.model.Album;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumDAO albumDAO;

    public Album addAlbum(Album newAlbum) {
        Album album = findBySpotifyId(newAlbum.getSpotify_id());

        if (album != null) {
            return album;
        }
        return albumDAO.save(newAlbum);
    }

    public Album findBySpotifyId(String spotifyId) {
        return getAllAlbums().stream()
                .filter(album -> album.getSpotify_id().equalsIgnoreCase(spotifyId))
                .findFirst()
                .orElse(null);
    }

    private List<Album> getAllAlbums() {
        return (List<Album>) albumDAO.findAllAlbums();
    }

    public Album getAlbumById(long albumId) {
        return albumDAO.findAlbumById(albumId).orElse(null);
    }

    public void deleteAlbumById(long albumId) {
        Optional<Album> maybeAlbum = albumDAO.findAlbumById(albumId);
        if (maybeAlbum.isEmpty())
            return;
        Album album = maybeAlbum.get();

        albumDAO.deleteAlbumById(album.getAlbum_id());
    }

    public Album getArtistByAlbum(String artist) {
        return albumDAO.findArtistByAlbum(artist).orElse(null);
    }
}
