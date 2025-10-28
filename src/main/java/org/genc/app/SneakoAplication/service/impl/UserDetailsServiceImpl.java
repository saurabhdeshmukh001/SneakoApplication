package org.genc.app.SneakoAplication.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.domain.entity.User;
import org.genc.app.SneakoAplication.dto.UserDetailsDTO;
import org.genc.app.SneakoAplication.enums.RoleType;
import org.genc.app.SneakoAplication.repo.UserRepository;
import org.genc.app.SneakoAplication.service.api.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private  final UserRepository userRepository;

    @Override
    public List<UserDetailsDTO> getAllUsers() {
        return userRepository.findAllByRoleName(RoleType.ROLE_CUSTOMER).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
    private UserDetailsDTO mapToDto(User u) {
        return UserDetailsDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .build();
    }

}
