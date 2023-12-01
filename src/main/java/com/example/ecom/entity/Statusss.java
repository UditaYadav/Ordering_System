package com.example.ecom.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name="status_show")
public class Statusss {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="status_id")
    private Long statusId;

    private StatuEnum statuseEnum;


  @Column(name="order_id")
    private Long orderId;



}
