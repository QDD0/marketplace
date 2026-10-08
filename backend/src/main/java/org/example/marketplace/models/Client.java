package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import org.example.marketplace.enums.UserRole;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "number")
    private String number;

    @Column(name = "date_create")
    @CreationTimestamp
    private LocalDateTime dateCreate;

    @Column(name = "avatar")
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @OneToMany(mappedBy = "client")
    private List<Review> reviews;

    @OneToMany(mappedBy = "client")
    private List<DeliveryAddress> deliveryAddresses;

    @OneToMany(mappedBy = "client")
    private List<CustomerOrder> customerOrders;
}
