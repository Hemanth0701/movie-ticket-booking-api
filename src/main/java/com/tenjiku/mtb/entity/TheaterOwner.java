package com.tenjiku.mtb.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@Getter
public class TheaterOwner extends UserDetails {

    @OneToMany(mappedBy = "theaterOwner", cascade = CascadeType.ALL)
    private   List<Theater> theaters = new ArrayList<>();



}
