package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.CartDTO;

public interface CartService {
    CartDTO getCartByUserId(Long userId);
    CartDTO recalculateCart(Long cartId);
}
