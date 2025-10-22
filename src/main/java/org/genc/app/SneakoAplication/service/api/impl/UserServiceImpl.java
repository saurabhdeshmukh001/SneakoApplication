package org.genc.app.SneakoAplication.service.api.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.genc.app.SneakoAplication.domain.entity.Users;
import org.genc.app.SneakoAplication.dto.RolesDTO;
import org.genc.app.SneakoAplication.dto.UsersDTO;
import org.genc.app.SneakoAplication.repo.RolesRepository;
import org.genc.app.SneakoAplication.repo.UsersRepository;
import org.genc.app.SneakoAplication.service.api.RolesService;
import org.genc.app.SneakoAplication.service.api.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {
    public  final UsersRepository usersRepository;
    public final    RolesService rolesService;
    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {
        Users users=getUserDetails(usersDTO);
        Users userObj=usersRepository.save(users);
        log.info("Created a User with id: {}",userObj.getUserID());

        return mapEmployeeEntityDTO(users);
    }
    private Users getUserDetails(UsersDTO usersDTO){
        Users userObj=
                Users.builder().userName(usersDTO.getUserName()).address(usersDTO.getAddress()).email(usersDTO.getEmail()).password(usersDTO.getPassword()).phoneNumber(usersDTO.getPhoneNumber()).build();
        Roles roleEntity=null;
        log.info("roll name:{}",usersDTO.getRolename());
        if((usersDTO.getRolename()!=null)){
            roleEntity=rolesService.findRoleEntityByName(usersDTO.getRolename());
        }
        userObj.setRoles(roleEntity != null ? List.of(roleEntity) : List.of());
        return userObj;

    }
    public UsersDTO mapEmployeeEntityDTO(Users users)
    {
        List<RolesDTO> roleDTOs = users.getRoles().stream()
                .map(role -> new RolesDTO(role.getRoleId(), role.getName()))
                .toList();
        String primaryRoleName = users.getRoles().stream()
                .map(Roles::getName)
                .findFirst() // Get the first role name
                .orElse(null);

        return new UsersDTO(
                users.getUserID(),
                users.getUserName(),
                users.getEmail(),
                users.getAddress(),
                users.getPassword(),
                users.getPhoneNumber(),
                primaryRoleName,
                roleDTOs
        );
    }
}
