package org.genc.app.SneakoAplication.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.dto.AnalyticsDTO;
import org.genc.app.SneakoAplication.dto.OrderDTO;
import org.genc.app.SneakoAplication.dto.ProductDTO;
import org.genc.app.SneakoAplication.dto.UserDetailsDTO;
import org.genc.app.SneakoAplication.repo.UserRepository;
import org.genc.app.SneakoAplication.service.api.OrderService;
import org.genc.app.SneakoAplication.service.api.ProductService;
import org.genc.app.SneakoAplication.service.api.UserDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserDetailsService userDetailsService;


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO>  findById( @PathVariable  Long id){
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productdto)
    {
        ProductDTO responseDTO=productService.createProduct(productdto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/product")
    public ResponseEntity<Page<ProductDTO>> getProducts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10")int size){
        Pageable pageable= PageRequest.of(page, size);
        Page<ProductDTO> ProductDTOPage=productService.getProduct(pageable);
        return  new ResponseEntity<>(ProductDTOPage, HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct( @PathVariable Long id,
                                                     @RequestBody ProductDTO productDTO){
        ProductDTO respdto=productService.updateProduct(id,productDTO);
        return new ResponseEntity<>(respdto,HttpStatus.OK);

    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/orders")
    public  ResponseEntity<Page<OrderDTO>> getOrders(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10")int size){
        Pageable pageable= PageRequest.of(page, size);
        Page<OrderDTO> OrderDTOPage=orderService.getOrders(pageable);
        return  new ResponseEntity<>(OrderDTOPage,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        List<UserDetailsDTO> users = userDetailsService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userDetailsService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestBody OrderDTO updatedOrder) {
        OrderDTO updated = orderService.updateOrderStatus(id, updatedOrder.getOrderStatus());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable Long id) {
        UserDetailsDTO user = userDetailsService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/analytics")
    public  ResponseEntity<AnalyticsDTO>  calculateAnayltics(){
        Long totalRevenue=orderService.calculateTotalRevenue();
        Long totalusers=userDetailsService.TotalUsers();
        Long totalOrders= orderService.totalOrders();
        Long totalProducts=productService.totalProduct();

        AnalyticsDTO analyticsDTO = new AnalyticsDTO(totalRevenue, totalOrders,totalusers,totalProducts);

        return ResponseEntity.ok(analyticsDTO);



    }

}
