package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "delivery_address")
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_address;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "apartment")
    private String apartment;
}