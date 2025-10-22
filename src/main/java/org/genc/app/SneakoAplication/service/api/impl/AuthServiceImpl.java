package org.genc.app.SneakoAplication.service.api.impl;

import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.genc.app.SneakoAplication.domain.entity.Users;
import org.genc.app.SneakoAplication.dto.AuthResponse;
import org.genc.app.SneakoAplication.dto.RegisterRequest;
import org.genc.app.SneakoAplication.repo.RolesRepository;
import org.genc.app.SneakoAplication.repo.UsersRepository;
import org.genc.app.SneakoAplication.security.JwtUtil;
import org.genc.app.SneakoAplication.service.api.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UsersRepository usersRepository,
                           RolesRepository rolesRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Users user = usersRepository.findByUserName(username).orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));
        String token = jwtUtil.generateToken(username, claims);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (usersRepository.existsByUserName(request.getUserName())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        Roles role = rolesRepository.findByName(request.getRolename())
                .orElseGet(() -> rolesRepository.save(Roles.builder().name(request.getRolename()).build()));

        Users user = Users.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(role))
                .build();

        Users saved = usersRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", saved.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));
        String token = jwtUtil.generateToken(saved.getUserName(), claims);
        return new AuthResponse(token);
    }
}
