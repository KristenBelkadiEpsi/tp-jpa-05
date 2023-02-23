package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Adresse")
@Getter
@Setter
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Integer numero;
    @Column(name = "rue")
    private String rue;
    @Column(name = "codePostal")
    private Integer codePostal;
    @Column(name = "ville")
    private String ville;

    @OneToOne(mappedBy = "adresse")
    private Client client;

}
