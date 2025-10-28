package org.genc.app.SneakoAplication.dto;

import lombok.Builder;
import lombok.Data;
import org.genc.app.SneakoAplication.enums.RoleType;

import java.util.Set;

@Data
@Builder
public class UserDetailsDTO {
    private Long id;
    private String username;
    private String email;
}
