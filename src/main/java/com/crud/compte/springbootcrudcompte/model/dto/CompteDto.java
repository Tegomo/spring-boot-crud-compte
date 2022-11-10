package com.crud.compte.springbootcrudcompte.model.dto;

import com.crud.compte.springbootcrudcompte.enums.TypeCompte;
import lombok.*;

import java.util.Date;

@Data
@Builder
public class CompteDto {
    private Long code;
    private double solde;
    private Date dateCreation;
    private TypeCompte type;
}
