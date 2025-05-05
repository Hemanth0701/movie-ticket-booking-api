package com.tenjiku.mtb.dto.exit_dto.screen;

import com.tenjiku.mtb.entity.Screen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatResponseDTO {

    private String seatId;

    private String name;

    private Screen screen;

}
