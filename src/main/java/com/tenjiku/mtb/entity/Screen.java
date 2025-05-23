package com.tenjiku.mtb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tenjiku.mtb.entity.enums.ScreenType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String screenId;

    private ScreenType screenType;

    private Integer capacity;

    private Integer noOfRows;


    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OrderBy(value = "name")
    @JsonIgnore
    private List<Seats> seats;

    @OneToMany(mappedBy = "screen", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Show> shows;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String createdBy;
}
