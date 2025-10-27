package org.genc.app.SneakoAplication.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.genc.app.SneakoAplication.domain.entity.Role;
import org.genc.app.SneakoAplication.domain.entity.User;
import org.genc.app.SneakoAplication.dto.UserRegistrationRequestDTO;
import org.genc.app.SneakoAplication.dto.UserRegistrationResponseDTO;
import org.genc.app.SneakoAplication.enums.RoleType;
import org.genc.app.SneakoAplication.exception.UserAlreadyExistsException;
import org.genc.app.SneakoAplication.repo.UserRepository;
import org.genc.app.SneakoAplication.service.api.RoleService;
import org.genc.app.SneakoAplication.service.api.UserMgmtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserMgmtServiceImpl implements UserMgmtService {

    private  final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegistrationResponseDTO registerNewUser(UserRegistrationRequestDTO userReqDTO) {
       Optional<User> existingUser = userRepository.findByUsername(userReqDTO.getUsername());
        User persUser = null;
        if(existingUser.isPresent() && isUserRoleExists(existingUser.get(),userReqDTO.getRoleType())) {
            log.error(" {}  already exists ", userReqDTO.getUsername());
            throw new UserAlreadyExistsException(userReqDTO.getUsername() +" already exists");
        }
        else if(existingUser.isPresent()) {
            User userEntity =  existingUser.get();
            userEntity.getRoles().add(roleService.getRoleByName(userReqDTO.getRoleType()));
            persUser = userRepository.save(userEntity);
            log.info("New  role {} added for  existing {}",userReqDTO.getRoleType(), existingUser.get().getAddress());
        }
        else {
            User user = User.builder().username(userReqDTO.getUsername()).password(passwordEncoder.encode(userReqDTO.getPassword()))
                    .Address(userReqDTO.getAddress()).phone(userReqDTO.getPhone())
                    .email(userReqDTO.getEmail())
                    .roles(Set.of(roleService.getRoleByName(userReqDTO.getRoleType())))
                    .build();
            persUser = userRepository.save(user);
        }
       //convert persUser  to UserRegistrationResponseDTO  and return
        StringBuilder welcomeMesaage = new StringBuilder(" Welcome ");
        welcomeMesaage.append(persUser.getAddress()).append(" ")
                .append("  to Genc  App");
        return UserRegistrationResponseDTO.builder()
                .id(persUser.getId())
                .username(persUser.getUsername())
                .address(persUser.getAddress())
                .phone(persUser.getPhone())
                .email(persUser.getEmail())
                .roles(persUser.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .userMessage(welcomeMesaage.toString())
                .build();
    }

    private boolean isUserRoleExists(User userObj, RoleType newRole) {
        boolean roleExists = userObj.getRoles().stream().anyMatch( r -> r.getName().equals(newRole));
        log.info("roleExists {}",roleExists);
        return  roleExists;
    }

    @Override
    public boolean isNewUser(String userName) {
        Optional<User> userObj =  userRepository.findByUsername("user");
        log.info("if User doesn't exists {} ", userObj.isEmpty());
        return userObj.isEmpty();
    }
}
