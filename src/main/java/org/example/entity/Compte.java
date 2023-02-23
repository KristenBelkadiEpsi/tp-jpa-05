package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Compte")
@Getter
@Setter

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "numero")
    private String numero;
    @Column(name = "solde")
    private Double solde;
    @ManyToMany
    private Set<Client> clients;

    @OneToMany(mappedBy = "compte")
    private Set<Operation> operations;

}
