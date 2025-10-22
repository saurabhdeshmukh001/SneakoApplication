package org.genc.app.SneakoAplication.service.api;


import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.genc.app.SneakoAplication.dto.RolesDTO;

public interface RolesService {
    public RolesDTO createRole(RolesDTO rolesDTO);
    public Roles findRoleEntityByName(String role);
}
