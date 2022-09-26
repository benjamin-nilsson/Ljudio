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
@Table(name = "artist")
public class Artist {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long artist_id;

    @Column
    private String spotify_id;

    @Column
    private String name;

    @Column
    @ManyToMany
    @JsonIgnore
    private List<Album> album_list;

}
