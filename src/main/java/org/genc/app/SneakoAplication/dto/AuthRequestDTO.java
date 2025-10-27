package org.genc.app.SneakoAplication.dto;

import lombok.Data;
import org.genc.app.SneakoAplication.enums.RoleType;

@Data
public class AuthRequestDTO {

    private String username;
    private String password;
    private String role;

}
