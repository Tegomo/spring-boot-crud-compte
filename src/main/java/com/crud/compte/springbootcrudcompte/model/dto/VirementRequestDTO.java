package com.crud.compte.springbootcrudcompte.model.dto;

import lombok.Data;

@Data
public class VirementRequestDTO {
    private  Long codeSource;
    private  Long codeDestination;
    private  double montant;
}
