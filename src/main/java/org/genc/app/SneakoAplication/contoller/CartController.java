package org.genc.app.SneakoAplication.contoller;

import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.dto.CartDTO;
import org.genc.app.SneakoAplication.service.api.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO> getCartByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PatchMapping("/{cartId}/recalculate")
    public ResponseEntity<CartDTO> recalculateCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.recalculateCart(cartId));
    }
}
