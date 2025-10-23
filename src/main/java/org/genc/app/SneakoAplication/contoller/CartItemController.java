package org.genc.app.SneakoAplication.contoller;

import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.domain.entity.CartItem;
import org.genc.app.SneakoAplication.dto.CartDTO;
import org.genc.app.SneakoAplication.dto.CartItemDTO;
import org.genc.app.SneakoAplication.service.api.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
public class CartItemController
{
    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItemDTO> addItemToCart(@RequestBody CartItemDTO cartItemDTO) {

        return  new ResponseEntity<>(cartItemService.createCartItem(cartItemDTO), HttpStatus.CREATED);

    }
    @PatchMapping
    public ResponseEntity<CartItemDTO> updateCartItemQuantity(@RequestBody CartItemDTO cartItemDTO) {
        // Returns 200 OK for a successful update operation
        CartItemDTO updatedItem = cartItemService.updateCartItemQuantity(cartItemDTO);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

   @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> findCartItemById(@PathVariable @RequestParam Long id)
   {
       return  new ResponseEntity<>(cartItemService.findCartItemById(id),HttpStatus.OK);
   }



}

