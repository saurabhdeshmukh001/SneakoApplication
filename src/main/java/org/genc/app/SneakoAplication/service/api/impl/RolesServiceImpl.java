package org.genc.app.SneakoAplication.service.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.genc.app.SneakoAplication.dto.RolesDTO;
import org.genc.app.SneakoAplication.repo.RolesRepository;
import org.genc.app.SneakoAplication.service.api.RolesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolesServiceImpl implements RolesService {
     private  final RolesRepository rolesRepository;

    @Override
    public RolesDTO createRole(RolesDTO rolesDTO) {
        Roles roleEntity=new Roles();
        roleEntity.setRoleId(rolesDTO.getRoleId());
        roleEntity.setName(rolesDTO.getRole());
        Roles roleObj=rolesRepository.save(roleEntity);
        log.info("New Category creted with the :{}",roleObj.getRoleId());
        RolesDTO responseDTO=new RolesDTO(roleObj.getRoleId(),roleObj.getName());
        return responseDTO;
    }

    @Override
    public Roles findRoleEntityByName(String role) {
//        Optional<Roles> RolesOptional = rolesRepository.findByName(role);
//
//        return RolesOptional
//                .orElseThrow(() -> new RuntimeException("Category Not Found by name: " + role));
        return rolesRepository.findByName(role).orElse(null); // Return null instead of throwing RuntimeException
    }



}
