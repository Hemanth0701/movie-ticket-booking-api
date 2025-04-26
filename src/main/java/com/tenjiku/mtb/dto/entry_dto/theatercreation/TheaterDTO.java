package com.tenjiku.mtb.dto.entry_dto.theatercreation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class TheaterDTO {

    @NotBlank(message = "Theater name must not be blank")
    @Size(max = 100, message = "Theater name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Address must not be blank")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotBlank(message = "City must not be blank")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @NotBlank(message = "Landmark must not be blank")
    @Size(max = 100, message = "Landmark must not exceed 100 characters")
    private String landmark;

}
