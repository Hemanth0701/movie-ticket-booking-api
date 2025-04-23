package com.tenjiku.mtb.dto.exit_dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tenjiku.mtb.dto.entry_dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDTO.class, name = "USER"),
        @JsonSubTypes.Type(value = TheaterOwnerResponseDTO.class, name = "THEATER_OWNER")
})
public class UserDetailsResponseDTO {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate Dob;
    private String role;
    private LocalDateTime createdAt;
}

