package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column(name = "name_item")
    private String nameItem;

    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @Column(name = "description")
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "characteristic")
    private Map<String, Object> characteristic;

    @Column(name = "price")
    private Double price;

    @Column(name = "count_item")
    private Integer countItem;

    @Column(name = "date_create")
    @CreationTimestamp
    private LocalDateTime dateCreate;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "item")
    private List<Review> reviews;

    @OneToMany(mappedBy = "item")
    private List<PhotoItem> photoItems;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
