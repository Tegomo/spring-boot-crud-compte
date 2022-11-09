package com.crud.compte.springbootcrudcompte.model.entities;

import com.crud.compte.springbootcrudcompte.enums.TypeCompte;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @ToString
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;
    private double solde;
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
}
