package com.tenjiku.mtb.dto.entry_dto.screen;

import com.tenjiku.mtb.entity.enums.ScreenType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ScreenDTO {

    @NotNull(message = "Screen type is required")
    private ScreenType screenType;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @NotNull(message = "Number of rows is required")
    @Min(value = 1, message = "Number of rows must be at least 1")
    private Integer noOfRows;


}
