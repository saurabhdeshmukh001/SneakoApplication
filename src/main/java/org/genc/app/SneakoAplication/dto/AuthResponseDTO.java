package org.genc.app.SneakoAplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String jwt;
    private String userId;
    private String address;
    private String role;
    private String email;
    private String phone;
    private String appInstance;

}
