package com.example.ljudio.dao;

import com.example.ljudio.model.Artist;
import com.example.ljudio.model.Song;
import com.example.ljudio.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ArtistDAO {

    private final ArtistRepository artistRepository;

    public Artist save(Artist newArtist) {
        return artistRepository.save(newArtist);
    }

    public Iterable<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    public void deleteSongFromAUsersList(long artistId) {
        artistRepository.deleteById(artistId);
    }

    public Optional<Artist> findArtistById(long songId) {
        return artistRepository.findById(songId);
    }

    public void deleteArtistById(long artistId) {
        artistRepository.deleteById(artistId);
    }

    public Optional<Artist> findArtistBySpotifyId(long spotifyId) {
        return artistRepository.findById(spotifyId);
    }

    public Optional<Artist> findArtistByName(String name) {
        return artistRepository.findById(Long.valueOf(name));
    }

    public Optional<Artist> findAlbumByArtist(String album) {
        return artistRepository.findById(Long.valueOf(album));
    }

    public Optional<Artist> getArtistBySpotifyId(long spotifyId) {
        return artistRepository.findById(spotifyId);
    }
}
