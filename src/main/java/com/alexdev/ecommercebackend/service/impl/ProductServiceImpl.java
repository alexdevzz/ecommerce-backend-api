package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.model.entity.Product;
import com.alexdev.ecommercebackend.model.mapper.CategoryMapper;
import com.alexdev.ecommercebackend.model.mapper.OptionMapper;
import com.alexdev.ecommercebackend.model.mapper.ProductMapper;
import com.alexdev.ecommercebackend.repository.ProductRepository;
import com.alexdev.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private OptionMapper optionMapper;


    @Override
    public List<ProductDTO> getProductsDTO(Pageable pageable) {
        return productMapper.toProductDtos(productRepository.findAll(pageable).getContent());
    }

    @Override
    public List<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        productDTO.setId(0);
        productDTO.setCreationDate(new Date());
        productDTO.setOptions(new ArrayList<>());
        productDTO.setCategories(new ArrayList<>());
        productDTO.setOrdersDetails(new ArrayList<>());
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
    public ProductDTO subtractStock(int productId, int quantity) {
        Product product = productRepository.findById(productId).get();
        product.setStock(product.getStock() - quantity);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO getProductDTO(int id) {
        Product product = productRepository.findById(id).get();
        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDTO getProductDTOBySku(String sku) {
        return productMapper.toProductDto(productRepository.getBySkuIgnoreCase(sku));
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findById(id).get();
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

    @Override
    public int count() {
        return (int) productRepository.count();
    }


    @Override
    public ProductDTO addCategory(int id, CategoryDTO categoryDTO) {
        Product product = productMapper.toProduct(getProductDTO(id));
        product.getCategories().add(categoryMapper.toCategory(categoryDTO));

        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO addOption(int id, OptionDTO optionDTO) {
        Product product = productMapper.toProduct(getProductDTO(id));
        product.getOptions().add(optionMapper.toOption(optionDTO));

        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO removeCategory(int id, CategoryDTO categoryDTO) {
        Product product = productRepository.findById(id).get();
        if (product.getCategories().remove(categoryMapper.toCategory(categoryDTO)))
            return productMapper.toProductDto(productRepository.save(product));
        else
            throw new EmptyException("category not found in product");

    }

    @Override
    public ProductDTO removeOption(int id, OptionDTO optionDTO) {
        Product product = productRepository.findById(id).get();
        if (product.getOptions().remove(optionMapper.toOption(optionDTO)))
            return productMapper.toProductDto(productRepository.save(product));
        else
            throw new EmptyException("option not found in product");

    }
}
