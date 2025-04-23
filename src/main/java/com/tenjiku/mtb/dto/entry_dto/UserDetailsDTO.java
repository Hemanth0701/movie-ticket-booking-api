package com.tenjiku.mtb.dto.entry_dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tenjiku.mtb.entity.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDTO.class, name = "USER"),
        @JsonSubTypes.Type(value = TheaterOwnerDTO.class, name = "THEATER_OWNER")
})
public class UserDetailsDTO {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate Dob;
    private Role role;
}

