package com.tenjiku.mtb.entity;

import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@Getter
public class User extends UserDetails{

}
