package fr.eni.cave.bo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"id", "nom", "petillant", "millesime", "quantite", "prix"})
@Builder

@Entity
@Table(name = "CAV_BOTTLE")
public class Bouteille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOTTLE_ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 250)
    private String nom;

    @Column(name = "SPARKLING")
    private boolean petillant;

    @Column(name = "VINTAGE", length = 100)
    private String millesime;

    @Column(name = "QUANTITY")
    private int quantite;

    @Column(name = "PRICE", precision = 2)
    private float prix;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Couleur couleur;

}
