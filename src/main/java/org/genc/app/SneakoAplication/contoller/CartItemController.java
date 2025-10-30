package org.genc.app.SneakoAplication.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.domain.entity.CartItem;
import org.genc.app.SneakoAplication.dto.CartItemDTO;
import org.genc.app.SneakoAplication.repo.CartItemRepository;
import org.genc.app.SneakoAplication.service.api.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
public class CartItemController
{
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;
    @GetMapping("/test-cart-item")
    public ResponseEntity<?> testCartItem() {
        try {
            List<CartItem> items = cartItemRepository.findExistingItems(1L, 1L, 5);
            return ResponseEntity.ok(items.isEmpty() ? "Not found" : items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




    @PostMapping
    public ResponseEntity<CartItemDTO> addItemToCart( @Valid @RequestBody CartItemDTO cartItemDTO) {

        return  new ResponseEntity<>(cartItemService.createCartItem(cartItemDTO), HttpStatus.CREATED);

    }
    @PatchMapping
    public ResponseEntity<CartItemDTO> updateCartItemQuantity(@RequestBody CartItemDTO cartItemDTO) {
        // Returns 200 OK for a successful update operation
        CartItemDTO updatedItem = cartItemService.updateCartItemQuantity(cartItemDTO);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> findCartItemById(@PathVariable Long id)
    {
        return  new ResponseEntity<>(cartItemService.findCartItemById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
