package org.genc.app.SneakoAplication.contoller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.dto.ProductDTO;
import org.genc.app.SneakoAplication.service.api.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productdto)
    {
        ProductDTO responseDTO=productService.createProduct(productdto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<Page<ProductDTO>> getProducts(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10")int size){
        Pageable pageable= PageRequest.of(page, size);
        Page<ProductDTO> ProductDTOPage=productService.getProduct(pageable);
        return  new ResponseEntity<>(ProductDTOPage,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct( @PathVariable Long id,
                                                    @RequestBody ProductDTO productDTO){
        ProductDTO respdto=productService.updateProduct(id,productDTO);
        return new ResponseEntity<>(respdto,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }

}
