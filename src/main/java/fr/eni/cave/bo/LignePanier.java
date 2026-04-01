package fr.eni.cave.bo;

import fr.eni.cave.bo.vin.Bouteille;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Builder

@Entity
@Table(name = "CAV_LINE")
public class LignePanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID")
    private Integer id;

    @Column(name = "QUANTITY")
    private int quantite;

    @Column(name = "PRICE", precision = 2)
    private float prix;

    @ManyToOne
    @JoinColumn(name = "BOTTLE_ID")
    private Bouteille bouteille;
}
