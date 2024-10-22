package com.alexdev.ecommercebackend.repository;

import com.alexdev.ecommercebackend.model.entity.OrderDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDatesRepository extends JpaRepository<OrderDates, Integer> {
}
