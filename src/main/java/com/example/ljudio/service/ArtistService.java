package com.example.ljudio.service;

import com.example.ljudio.dao.ArtistDAO;
import com.example.ljudio.dao.SongDAO;
import com.example.ljudio.model.Artist;
import com.example.ljudio.model.Song;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistDAO artistDAO;
    private final SongDAO songDAO;

    public Artist addArtist(Artist newArtist) {
        Artist artist = findBySpotifyId(newArtist.getSpotify_id());

        if (artist != null) {
            return artist;
        }
        return artistDAO.save(newArtist);
    }

    private Artist findBySpotifyId(String spotifyId) {
        return getAllArtists().stream()
                .filter(artist -> artist.getSpotify_id().equalsIgnoreCase(spotifyId))
                .findFirst()
                .orElse(null);
    }

    private List<Artist> getAllArtists() {
        return (List<Artist>) artistDAO.findAllArtists();
    }

    public Artist addArtistToSong(long artistId, long id) {
        Optional<Artist> maybeArtist = artistDAO.findArtistById(artistId);
        Optional<Song> maybeSong = songDAO.findSongById(id);
        if (maybeArtist.isEmpty() || maybeSong.isEmpty()) {
            return null;
        }

        Artist artist = maybeArtist.get();
        Song song = maybeSong.get();

        List<Artist> artists = song.getArtist_list();
        artists.add(artist);
        song.setArtist_list(artists);
        return artistDAO.save(artist);
    }

    public Artist getArtistByID(long artistId) {
        return artistDAO.findArtistById(artistId).orElse(null);
    }

    public void deleteArtistById(long artistId) {
        Optional<Artist> maybeArtist = artistDAO.findArtistById(artistId);
        if (maybeArtist.isEmpty())
            return;
        Artist artist = maybeArtist.get();

        artistDAO.deleteArtistById(artist.getArtist_id());
    }

    public Artist getArtistBySpotifyId(long spotifyId) {
        return artistDAO.getArtistBySpotifyId(spotifyId).orElse(null);
    }

    public Artist getArtistByName(String name) {
        return artistDAO.findArtistByName(name).orElse(null);
    }

    public Artist getAlbumByArtist(String album) {
        return artistDAO.findAlbumByArtist(album).orElse(null);
    }
}