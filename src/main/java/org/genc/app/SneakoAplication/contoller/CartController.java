package org.genc.app.SneakoAplication.contoller;

import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.dto.CartDTO;
import org.genc.app.SneakoAplication.service.api.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
