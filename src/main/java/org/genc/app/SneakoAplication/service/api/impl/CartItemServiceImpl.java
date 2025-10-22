package org.genc.app.SneakoAplication.service.api.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.genc.app.SneakoAplication.domain.entity.CartItem;
import org.genc.app.SneakoAplication.domain.entity.Category;
import org.genc.app.SneakoAplication.dto.CartItemDTO;
import org.genc.app.SneakoAplication.dto.CategoryDTO;
import org.genc.app.SneakoAplication.repo.CartItemRepository;
import org.genc.app.SneakoAplication.service.api.CartItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
   private final CartItemRepository cartItemRepository;
    @Override
    public CartItemDTO createCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItemEntity=new CartItem();
        cartItemEntity.setCartItemId(cartItemDTO.getCartItemId());
        cartItemEntity.setProductId(cartItemDTO.getProductId());
        cartItemEntity.setUnitPrice(cartItemDTO.getUnitPrice());
        cartItemEntity.setQuantity(1L);
        cartItemEntity.setTotalPrice(cartItemDTO.getTotalPrice());
        CartItem cartItemObj=cartItemRepository.save(cartItemEntity);
        log.info("New Category creted with the :{}",cartItemObj.getCartItemId());
        CartItemDTO responseDTO=new CartItemDTO(cartItemObj.getCartItemId(),cartItemObj.getProductId(),
                cartItemObj.getUnitPrice(),
                cartItemObj.getQuantity(),cartItemObj.getTotalPrice());
        return responseDTO;

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
