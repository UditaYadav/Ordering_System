package com.example.ecom.service.integration.REpo;

import com.example.ecom.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface itemRepository extends JpaRepository<Items,Long> {


    public Items findByItemId(Long id);

}
