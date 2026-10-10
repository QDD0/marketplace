package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import org.example.marketplace.enums.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "date_order")
    @CreationTimestamp
    private LocalDateTime dateOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
