package org.genc.app.SneakoAplication.service.api.impl;

import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.genc.app.SneakoAplication.domain.entity.Users;
import org.genc.app.SneakoAplication.dto.UsersDTO;
import org.genc.app.SneakoAplication.repo.UsersRepository;
import org.genc.app.SneakoAplication.service.api.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {
        Users user = Users.builder()
                .userName(usersDTO.getUserName())
                .email(usersDTO.getEmail())
                .address(usersDTO.getAddress())
                .phoneNumber(usersDTO.getPhoneNumber())
                .password(usersDTO.getPassword())
                .roles(usersDTO.getRoles() == null ? List.of() :
                        usersDTO.getRoles().stream().map(r -> {
                            Roles role = new Roles();
                            role.setName(r);
                            return role;
                        }).collect(Collectors.toList()))
                .build();

        Users saved = usersRepository.save(user);
        return mapToDto(saved);
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!usersRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        usersRepository.deleteById(id);
    }

    private UsersDTO mapToDto(Users u) {
        UsersDTO dto = new UsersDTO();
        // adjust getter name if your Users entity uses a different id field name
        dto.setId(u.getUserID());
        dto.setUserName(u.getUserName());
        dto.setEmail(u.getEmail());
        dto.setAddress(u.getAddress());
        dto.setPhoneNumber(u.getPhoneNumber());
        if (u.getRoles() != null) {
            dto.setRoles(u.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));
        }
        return dto;
    }
}
