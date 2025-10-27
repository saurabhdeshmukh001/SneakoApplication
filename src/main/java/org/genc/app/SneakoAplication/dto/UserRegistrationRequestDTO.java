package org.genc.app.SneakoAplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.genc.app.SneakoAplication.enums.RoleType;

@Data
@AllArgsConstructor
public class UserRegistrationRequestDTO {

    private String username;
    private String password;
    private String email;
    private RoleType roleType;
    private String address;
    private String phone;

}
