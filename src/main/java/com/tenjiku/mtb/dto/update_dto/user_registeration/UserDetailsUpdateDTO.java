package com.tenjiku.mtb.dto.update_dto.user_registeration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserUpdateDTO.class, name = "USER"),
        @JsonSubTypes.Type(value = TheaterOwnerUpdateDTO.class, name = "THEATER_OWNER")
})
public class UserDetailsUpdateDTO {

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be valid (10–15 digits, optional +)"
    )
    private String phoneNumber;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;


}

