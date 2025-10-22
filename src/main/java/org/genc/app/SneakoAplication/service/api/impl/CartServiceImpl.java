package org.genc.app.SneakoAplication.service.api.impl;

import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.domain.entity.Cart;
import org.genc.app.SneakoAplication.domain.entity.CartItem;
import org.genc.app.SneakoAplication.dto.CartDTO;
import org.genc.app.SneakoAplication.dto.CartItemDTO;
import org.genc.app.SneakoAplication.repo.CartRepository;
import org.genc.app.SneakoAplication.service.api.CartService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        Set<CartItemDTO> itemDTOs = cart.getCartItems().stream().map(item ->
                CartItemDTO.builder()
                        .cartItemId(item.getCartItemId())
                        .productId(item.getProductId())
                        .unitPrice(item.getUnitPrice())
                        .quantity(item.getQuantity())
                        .totalPrice(item.getTotalPrice())
                        .build()
        ).collect(Collectors.toSet());

        return new CartDTO(cart.getId(), cart.getUserId(), itemDTOs, cart.getTotalPrice(), cart.getTotalItem());
    }

    @Override
    public CartDTO recalculateCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getTotalPrice())
                .sum();

        int totalItems = cart.getCartItems().size();

        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItems);

        cartRepository.save(cart);

        return getCartByUserId(cart.getUserId());
    }
}
