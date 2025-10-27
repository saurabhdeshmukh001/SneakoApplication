package org.genc.app.SneakoAplication.repo;

import org.genc.app.SneakoAplication.domain.entity.Role;
import org.genc.app.SneakoAplication.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
