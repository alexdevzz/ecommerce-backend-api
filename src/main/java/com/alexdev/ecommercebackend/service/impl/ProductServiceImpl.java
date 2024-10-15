package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.model.entity.Product;
import com.alexdev.ecommercebackend.model.mapper.ProductMapper;
import com.alexdev.ecommercebackend.repository.ProductRepository;
import com.alexdev.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getProducts() {
        return productMapper.toProductDtos(productRepository.findAll());
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        productDTO.setId(0);
        productDTO.setCreationDate(new Date());
        Product product = productMapper.toProduct(productDTO);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO update(int productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId).get();
        productDTO.setId(productId);
        productMapper.updateProduct(product, productDTO);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO getProduct(int id) {
        Product product = productRepository.findById(id).get();
        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDTO delete(int id) {
        Product product = productRepository.findById(id).get();
        ProductDTO productDTO = productMapper.toProductDto(product);
        productRepository.delete(product);
        return productDTO;
    }

    @Override
    public boolean existsById(int id) {
        return productRepository.existsById(id);
    }
}
