package org.genc.app.SneakoAplication.dto;

import lombok.Builder;
import lombok.Data;
import org.genc.app.SneakoAplication.enums.RoleType;


import java.util.Set;

@Data
@Builder
public class UserRegistrationResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String userMessage;
    private Set<RoleType> roles;
}
