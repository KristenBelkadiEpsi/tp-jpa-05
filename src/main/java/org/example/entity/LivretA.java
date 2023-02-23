package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LivretA extends Compte{
    @Column(name="taux")
    private Double taux;
}
