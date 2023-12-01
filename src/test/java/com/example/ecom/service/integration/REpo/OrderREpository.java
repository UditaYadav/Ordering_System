package com.example.ecom.service.integration.REpo;

import com.example.ecom.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderREpository extends JpaRepository<Orders,Long> {
}
