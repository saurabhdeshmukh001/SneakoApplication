package org.genc.app.SneakoAplication.repo;

import jakarta.validation.constraints.NotNull;
import org.genc.app.SneakoAplication.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartItemIdAndProductId(Long cartitemId, Long productId);
}
