package fr.eni.cave.bo.client;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Builder

@Entity
@Table(name = "CAV_SHOPPING_CART")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Integer id;

    @Column(name = "ORDER_NUMBER", length = 200)
    private String numCommande;

    @Column(name = "TOTAL_PRICE", precision = 2)
    private float prixTotal;

    @Column(name = "PAID")
    private boolean paye;

    //le losange noir se traduit par le cascade (il supprime les lignes associées au panier) et orphan prend les lignes qui ont été dissociées
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<LignePanier> lignesPanier = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch =  FetchType.EAGER)
    @JoinColumn(name = "LOGIN")
    private Client client;
}
