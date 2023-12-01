package com.example.ecom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")


public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;
    @Column(name="order_date")
    @CreationTimestamp
    private LocalDate orderDate;
    @Column(name="client_name")
    private String clientName;

     @Enumerated(EnumType.STRING)
     private StatuEnum statusss;

    @Column(name="client_address")
    private String clientAddress;
    @UpdateTimestamp
    @Column(name="order_dispatch")
    private String dispatchDate;
    @Column(name="order_amount")
    private float orderAmount;
    @Column(name="number_of_items")
    private Integer numberOfItems;
    @Column(name="created_at")
    @UpdateTimestamp
    private LocalDate createdAt;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName ="order_id",nullable = false )

    private Set<Items> itemList ;



}