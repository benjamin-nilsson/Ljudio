package com.example.ljudio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long album_id;

    @Column
    private String spotify_id;

    @Column
    private String name;

    @Column
    private int songs;

    @Column
    private String album_length;

    @Column
    private Artist artist;

    @Column
    private AlbumImage albumImage;

    @Column
    @ManyToMany
    @JsonIgnore
    private List<Song> songs_list;
}
