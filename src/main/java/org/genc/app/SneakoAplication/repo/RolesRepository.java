package org.genc.app.SneakoAplication.repo;


import org.genc.app.SneakoAplication.domain.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
}
