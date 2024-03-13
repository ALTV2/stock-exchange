package com.tveritin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String username;

    @Column
    private String Name;

    @Column
    private String SurName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Double balance;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Portfolio portfolio;
}
