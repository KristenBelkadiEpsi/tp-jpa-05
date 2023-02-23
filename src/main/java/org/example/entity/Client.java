package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;

    @Column(name="dateNaissance")
    @Temporal(TemporalType.DATE)
    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name="banqueId", nullable=false)
    private Banque banque;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adreseId", referencedColumnName = "id")
    private Adresse adresse;


    @ManyToMany
    private Set<Compte> comptes;
}
