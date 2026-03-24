package fr.eni.cave.bo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//pour éviter les boucles infinies
@ToString(exclude = {"client"})
@EqualsAndHashCode(exclude = {"client"})

@Entity
@Table(name = "CAV_ADDRESS")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private int id;

    @Column(name = "STREET", nullable = false, length = 250)
    private String rue;

    @Column(name = "POSTAL_CODE", nullable = false, length = 5)
    private String codePostal;

    @Column(name = "CITY", nullable = false, length = 150)
    private String ville;

    @OneToOne(mappedBy = "address")
    private Client client;
}
