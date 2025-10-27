package org.genc.app.SneakoAplication.service.api;


import org.genc.app.SneakoAplication.domain.entity.Role;
import org.genc.app.SneakoAplication.dto.RoleRequestDTO;
import org.genc.app.SneakoAplication.dto.RoleResponseDTO;
import org.genc.app.SneakoAplication.enums.RoleType;

import java.util.List;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO request);
    RoleResponseDTO getRoleById(Long id);
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO updateRole(Long id, RoleRequestDTO request);
    void deleteRole(Long id);
    Role getRoleByName(RoleType roleType);
    public Role seedRoleData(RoleRequestDTO request);
}
