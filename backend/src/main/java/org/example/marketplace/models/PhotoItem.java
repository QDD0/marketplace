
package org.example.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "photo_item")
public class PhotoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "photo_order")
    private Integer photo_order;
}