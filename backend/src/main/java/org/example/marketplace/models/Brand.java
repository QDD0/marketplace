package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;

    @Column(name = "name_brand")
    private String nameBrand;

    @OneToMany(mappedBy = "brand")
    private List<Item> items;
}
