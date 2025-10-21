package org.genc.app.SneakoAplication.repo;

import org.genc.app.SneakoAplication.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
