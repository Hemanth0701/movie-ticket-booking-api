package com.tenjiku.mtb.dto.exit_dto.screen;

import com.tenjiku.mtb.entity.Theater;
import com.tenjiku.mtb.entity.enums.ScreenType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@ToString
public class ScreenResponseDTO {
    private String screenId;
    private ScreenType screenType;
    private Integer capacity;
    private Integer noOfRows;
    private Theater theater;
    private List<SeatResponseDTO> seats;
    private LocalDateTime createdAt;
    private String createdBy;
}
