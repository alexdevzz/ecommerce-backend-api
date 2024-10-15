package com.alexdev.ecommercebackend.model.mapper.impl;

import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.model.entity.Product;
import com.alexdev.ecommercebackend.model.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
    }

    @Override
    public ProductDTO toProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public List<ProductDTO> toProductDtos(List<Product> products) {
        if (products == null) {
            return null;
        }

        List<ProductDTO> productDTOS = new ArrayList<>(products.size());
        for (Product product : products) {
            productDTOS.add(toProductDto(product));
        }
        return productDTOS;
    }

    @Override
    public void updateProduct(Product product, ProductDTO productDTO) {
        if (productDTO == null) {
            return;
        }

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
    }
}
