package com.example.ecom.repo;

import com.example.ecom.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface itemRepository extends JpaRepository<Items,Long> {
    public Items findByItemId(Long id);

}
