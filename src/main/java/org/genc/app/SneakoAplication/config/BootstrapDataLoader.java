package org.genc.app.SneakoAplication.config;

import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.genc.app.SneakoAplication.domain.entity.Users;
import org.genc.app.SneakoAplication.repo.RolesRepository;
import org.genc.app.SneakoAplication.repo.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class BootstrapDataLoader {

    @Bean
    public CommandLineRunner seed(RolesRepository rolesRepository, UsersRepository usersRepository, PasswordEncoder encoder) {
        return args -> {
            Roles adminRole = rolesRepository.findByName("ADMIN").orElseGet(() -> rolesRepository.save(Roles.builder().name("ADMIN").build()));
            Roles userRole = rolesRepository.findByName("USER").orElseGet(() -> rolesRepository.save(Roles.builder().name("USER").build()));

            if (usersRepository.findByUserName("admin").isEmpty()) {
                Users admin = Users.builder()
                        .userName("admin")
                        .email("admin@example.com")
                        .password(encoder.encode("Admin@123"))
                        .roles(List.of(adminRole))
                        .build();
                usersRepository.save(admin);
            }
        };
    }
}
