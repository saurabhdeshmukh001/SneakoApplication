package org.genc.app.SneakoAplication.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RolesDTO {
    @Nullable
    private Long roleId;

    @NotBlank(message = "Role Cant be Empty")
    private String role;


}
