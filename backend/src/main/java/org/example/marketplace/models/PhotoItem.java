package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "photo_item")
public class PhotoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoto;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "photo_order")
    private Integer photo_order;
}
