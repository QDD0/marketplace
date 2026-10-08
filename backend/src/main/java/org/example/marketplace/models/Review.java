package org.example.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_review;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @Column(name = "description_review")
    private String descriptionReview;

    @Column(name = "photo")
    private String photo;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "date_review")
    @CreationTimestamp
    private LocalDateTime dateReview;
}
