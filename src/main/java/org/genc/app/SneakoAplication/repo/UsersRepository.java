package org.genc.app.SneakoAplication.repo;

import org.genc.app.SneakoAplication.domain.entity.Users;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{
    Optional<Users> findByUserName(String userName);
    Optional<Users> findByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
