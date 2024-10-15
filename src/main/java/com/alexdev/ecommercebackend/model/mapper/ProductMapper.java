package com.alexdev.ecommercebackend.model.mapper;

import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.model.entity.Product;

import java.util.List;

public interface ProductMapper {

    Product toProduct(ProductDTO productDTO);

    ProductDTO toProductDto(Product product);

    List<ProductDTO> toProductDtos(List<Product> products);

    void updateProduct(Product product, ProductDTO productDTO);
}
