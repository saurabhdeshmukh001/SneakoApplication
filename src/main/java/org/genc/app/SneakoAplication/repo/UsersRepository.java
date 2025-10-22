package org.genc.app.SneakoAplication.repo;

import org.genc.app.SneakoAplication.domain.entity.Users;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{
}
