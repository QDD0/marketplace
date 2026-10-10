
package org.example.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;

    @Column(name = "name_brand")
    private String nameBrand;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Item> items;
}
