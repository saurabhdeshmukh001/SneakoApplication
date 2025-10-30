package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.CartItemDTO;

public interface CartItemService {
    CartItemDTO createCartItem(CartItemDTO cartItemDTO);
    CartItemDTO updateCartItemQuantity(CartItemDTO cartItemDTO);
    CartItemDTO findCartItemById(Long id);
    void deleteCartItem(Long id);
}
