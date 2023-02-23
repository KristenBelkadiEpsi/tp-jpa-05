package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
public class AssuranceVie extends Compte{
    @Column(name="dateFin")
    @Temporal(TemporalType.DATE)
    private LocalDate dateFin;
    @Column(name="taux")
    private Double taux;
}
