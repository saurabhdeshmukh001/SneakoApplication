package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.CartItemDTO;

public interface CartItemService {
    public CartItemDTO createCartItem(CartItemDTO cartItemDTO);
    public CartItemDTO updateCartItemQuantity(CartItemDTO cartItemDTO);
    public CartItemDTO findCartItemById(Long id);

}
