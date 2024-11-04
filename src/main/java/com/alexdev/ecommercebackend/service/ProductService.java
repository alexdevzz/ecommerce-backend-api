package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.model.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProductsDTO(Pageable pageable);

    List<Product> getProducts(Pageable pageable);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(int productId, ProductDTO productDTO);

    ProductDTO getProductDTO(int id);

    ProductDTO getProductDTOBySku(String sku);

    Product getProduct(int id);

    ProductDTO delete(int ProductDTOId);

    boolean existsById(int id);

    int count();


    ProductDTO addCategory(int id, CategoryDTO categoryDTO);

    ProductDTO addOption(int id, OptionDTO optionDTO);

    ProductDTO removeCategory(int id, CategoryDTO categoryDTO);

    ProductDTO removeOption(int id, OptionDTO optionDTO);
}
