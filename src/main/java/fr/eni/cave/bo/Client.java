package fr.eni.cave.bo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//pour inclure uniquement les champs ci-après
@ToString(of = {"pseudo", "nom", "prenom"})
@EqualsAndHashCode(of = {"pseudo"})
@Builder

@Entity
@Table(name = "CAV_CLIENT")
public class Client {

    @Id
    @Column(name = "LOGIN", length = 255, nullable = false)
    private String pseudo;

    @Column(name = "PASSWORD", length = 150, nullable = false)
    private String password;

    @Column(name = "LAST_NAME", length = 90, nullable = false)
    private String nom;

    @Column(name = "FIRST_NAME",  length = 150, nullable = false)
    private String prenom;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Adresse address;
}