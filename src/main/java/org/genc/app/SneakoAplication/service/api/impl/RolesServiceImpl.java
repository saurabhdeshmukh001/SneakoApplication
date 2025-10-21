package org.genc.app.SneakoAplication.service.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.genc.app.SneakoAplication.domain.entity.Category;
import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.genc.app.SneakoAplication.dto.CategoryDTO;
import org.genc.app.SneakoAplication.dto.RolesDTO;
import org.genc.app.SneakoAplication.repo.RolesRepository;
import org.genc.app.SneakoAplication.service.api.RolesService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolesServiceImpl implements RolesService {
     private  final RolesRepository rolesRepository;
    @Override
    public RolesDTO createRole(RolesDTO rolesDTO) {
        Roles roleEntity=new Roles();
        roleEntity.setRoleId(rolesDTO.getRoleId());
        roleEntity.setRole(rolesDTO.getRole());
        Roles roleObj=rolesRepository.save(roleEntity);
        log.info("New Category creted with the :{}",roleObj.getRoleId());
        RolesDTO responseDTO=new RolesDTO(roleObj.getRoleId(),roleObj.getRole());
        return responseDTO;
    }
}
