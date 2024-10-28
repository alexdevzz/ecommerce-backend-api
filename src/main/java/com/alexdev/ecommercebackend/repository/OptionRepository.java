package com.alexdev.ecommercebackend.repository;

import com.alexdev.ecommercebackend.model.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {

    boolean existsByNameIgnoreCase(String name);

    Option getByNameIgnoreCase(String name);
}
