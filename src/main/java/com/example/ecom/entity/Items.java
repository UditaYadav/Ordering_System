package com.example.ecom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;


@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "item_table")

public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_price")
    private float itemPrice;
    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @UpdateTimestamp
    @Column(name = "shipped_date")
    private String shippedDate;






}

