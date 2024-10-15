package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts();

    ProductDTO save(ProductDTO productDTO);

    ProductDTO update(int productId, ProductDTO productDTO);

    ProductDTO getProduct(int ProductDTOId);

    ProductDTO delete(int ProductDTOId);

    boolean existsById(int id);
}
