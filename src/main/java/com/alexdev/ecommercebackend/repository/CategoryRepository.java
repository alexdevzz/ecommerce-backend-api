package com.alexdev.ecommercebackend.repository;

import com.alexdev.ecommercebackend.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    boolean existsByNameIgnoreCase(String name);

    Category getByNameIgnoreCase(String name);
}
