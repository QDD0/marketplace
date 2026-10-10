package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderItem;

    @ManyToOne()
    @JoinColumn(name = "id_order")
    private CustomerOrder order;

    @ManyToOne()
    @JoinColumn(name = "id_item")
    private Item item;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
}
