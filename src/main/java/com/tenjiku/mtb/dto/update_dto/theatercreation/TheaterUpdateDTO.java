package com.tenjiku.mtb.dto.update_dto.theatercreation;

import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class TheaterUpdateDTO {

    @Size(max = 100, message = "Theater name must not exceed 100 characters")
    private String name;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @Size(max = 100, message = "Landmark must not exceed 100 characters")
    private String landmark;
}
