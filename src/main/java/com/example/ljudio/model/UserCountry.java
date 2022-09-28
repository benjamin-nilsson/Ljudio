package com.example.ljudio.model;

import com.example.ljudio.enums.Country;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "user_country")
public class UserCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long country_id;

    @Column
    @Enumerated(EnumType.STRING)
    private Country country;
}
