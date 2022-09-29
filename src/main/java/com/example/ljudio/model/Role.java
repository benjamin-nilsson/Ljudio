package com.example.ljudio.model;

import com.example.ljudio.enums.ERole;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte role_id;

    @Column
    @Enumerated(EnumType.STRING)
    private ERole name;
}
