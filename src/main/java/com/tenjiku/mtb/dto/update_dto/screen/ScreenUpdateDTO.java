package com.tenjiku.mtb.dto.update_dto.screen;

import com.tenjiku.mtb.entity.Seats;
import com.tenjiku.mtb.entity.Theater;
import com.tenjiku.mtb.entity.enums.ScreenType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class ScreenUpdateDTO {

    private ScreenType screenType;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @Min(value = 1, message = "Number of rows must be at least 1")
    private Integer noOfRows;
}
