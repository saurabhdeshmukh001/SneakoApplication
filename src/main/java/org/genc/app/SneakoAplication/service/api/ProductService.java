package org.genc.app.SneakoAplication.service.api;

import jakarta.validation.Valid;
import org.genc.app.SneakoAplication.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductService {
    public ProductDTO createProduct( ProductDTO productdto);
    public Page<ProductDTO> getProduct(Pageable pageable);
    public  ProductDTO updateProduct(Long id,ProductDTO productDTO);
    public void  deleteProduct(Long id);
    public ProductDTO findById(Long id);

}
