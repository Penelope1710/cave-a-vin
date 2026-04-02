package fr.eni.cave.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "CAV_OWNER")
public class Proprio extends Utilisateur{

    @Id
    @Column(name = "LOGIN", length = 255, nullable = false)
    private String pseudo;

    @Column(name = "CLIENT_NUMBER")
    private String siret;
}
