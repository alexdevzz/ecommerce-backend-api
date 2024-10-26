package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts(Pageable pageable);

    ProductDTO save(ProductDTO productDTO);

    ProductDTO update(int productId, ProductDTO productDTO);

    ProductDTO getProduct(int ProductDTOId);

    ProductDTO delete(int ProductDTOId);

    boolean existsById(int id);

    int count();

}
