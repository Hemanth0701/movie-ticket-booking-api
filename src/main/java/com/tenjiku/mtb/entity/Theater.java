package com.tenjiku.mtb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String name;
    private String address;
    private String city;
    private String landmark;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "theater_owner_id", nullable = false)
    private TheaterOwner theaterOwner;
}
