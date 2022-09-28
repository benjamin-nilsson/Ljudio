package com.example.ljudio.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AlbumImage")
public class AlbumImage {

    @Id
    @GeneratedValue
    private long image_id;

    @Column
    private String Image;
}
