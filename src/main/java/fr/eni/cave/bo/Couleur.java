package fr.eni.cave.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "CAV_COLOR")
public class Couleur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLOR_ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 250)
    private String nom;
}
