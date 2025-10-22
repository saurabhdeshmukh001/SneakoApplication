package org.genc.app.SneakoAplication.service.api.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.genc.app.SneakoAplication.domain.entity.Cart;
import org.genc.app.SneakoAplication.domain.entity.CartItem;
import org.genc.app.SneakoAplication.domain.entity.Category;
import org.genc.app.SneakoAplication.dto.CartItemDTO;
import org.genc.app.SneakoAplication.dto.CategoryDTO;
import org.genc.app.SneakoAplication.repo.CartItemRepository;
import org.genc.app.SneakoAplication.repo.CartRepository;
import org.genc.app.SneakoAplication.service.api.CartItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
   private final CartItemRepository cartItemRepository;
   private final CartRepository cartRepository;


    @Override
    public CartItemDTO createCartItem(CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findByUserId(cartItemDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productId(cartItemDTO.getProductId())
                .unitPrice(cartItemDTO.getUnitPrice())
                .quantity(cartItemDTO.getQuantity())
                .TotalPrice(cartItemDTO.getUnitPrice() * cartItemDTO.getQuantity())
                .build();

        cartItemRepository.save(cartItem);

        cart.getCartItems().add(cartItem);
        cart.setTotalItem(cart.getCartItems().size());
        cart.setTotalPrice(cart.getCartItems().stream().mapToDouble(CartItem::getTotalPrice).sum());
        cartRepository.save(cart);

        return CartItemDTO.builder()
                .cartItemId(cartItem.getCartItemId())
                .productId(cartItem.getProductId())
                .unitPrice(cartItem.getUnitPrice())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getTotalPrice())
                .build();
    }
    @Override
    @Transactional
    public CartItemDTO updateCartItemQuantity(CartItemDTO cartItemDTO) {
        // 1. Validate required identifiers (assuming DTO contains productId and cartId)
        if (cartItemDTO.getProductId() == null || cartItemDTO.getCartItemId()== null) {
            throw new IllegalArgumentException("Product ID and Cart ID are required for updating cart item.");
        }
        if (cartItemDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        // 2. Find the existing CartItem by its unique identifiers (e.g., cartId and productId)
        CartItem cartItem = cartItemRepository.findByCartItemIdAndProductId(
                cartItemDTO.getCartItemId(),
                cartItemDTO.getProductId()
        ).orElseThrow(() -> new RuntimeException(
                "CartItem not found for Cart ID: " + cartItemDTO.getCartItemId() +
                        " and Product ID: " + cartItemDTO.getProductId()));


        // 3. Update the quantity
        Long newQuantity = cartItemDTO.getQuantity();
        cartItem.setQuantity((long) newQuantity);

        // 4. Recalculate the total price based on the stored unitPrice
        // The entity must store the unitPrice at the time of addition.
        Long unitPrice = cartItemDTO.getUnitPrice();
        Long TotalPrice = unitPrice*newQuantity;
        cartItem.setTotalPrice(TotalPrice);

        // 5. Save the updated item
        CartItem updatedItem = cartItemRepository.save(cartItem);

        // 6. Convert and return the updated DTO (assuming cartItemMapper exists)
        // return cartItemMapper.toDto(updatedItem);
        // Using a placeholder return since the mapper is not defined
        cartItemDTO.setTotalPrice(updatedItem.getTotalPrice());
        return cartItemDTO;
    }

    @Override
    public CartItemDTO findCartItemById(Long id) {

        CartItem cartItem=cartItemRepository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        CartItemDTO cartItemDTO=new CartItemDTO();
        cartItemDTO.setCartItemId(cartItem.getCartItemId());
                cartItemDTO.setProductId(cartItem.getProductId());
                cartItemDTO.setQuantity(cartItem.getQuantity());
                cartItemDTO.setUnitPrice(cartItem.getUnitPrice());
                cartItemDTO.setTotalPrice(cartItem.getTotalPrice());

        return cartItemDTO;
    }
}
