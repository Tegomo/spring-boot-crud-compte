package com.crud.compte.springbootcrudcompte.auth.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddRoleToUserDTO {
    private  String userName;
    private  String roleName;
}
