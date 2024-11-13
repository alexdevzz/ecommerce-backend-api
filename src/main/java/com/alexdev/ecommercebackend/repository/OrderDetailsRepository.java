package com.alexdev.ecommercebackend.repository;

import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.model.entity.OrderDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    boolean existsByOrder_IdAndProduct_Id(int idOrder, int idProduct);

    OrderDetails findOrderDetailsByOrder_IdAndAndProduct_Sku(int idOrder, String productSku);
}
