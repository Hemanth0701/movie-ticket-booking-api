package com.tenjiku.mtb.mapper;

import com.tenjiku.mtb.dto.entry_dto.screen.ScreenDTO;
import com.tenjiku.mtb.dto.exit_dto.screen.ScreenResponseDTO;
import com.tenjiku.mtb.dto.exit_dto.screen.SeatResponseDTO;
import com.tenjiku.mtb.entity.Screen;
import com.tenjiku.mtb.entity.Seats;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ScreenMapper {

    public Screen toEntity(ScreenDTO screenDTO) {
        if ( screenDTO == null ) {
            return null;
        }

        Screen screen= new Screen();
        screen.setScreenType(screenDTO.getScreenType());
        screen.setCapacity(screenDTO.getCapacity());
        screen.setNoOfRows(screenDTO.getNoOfRows());
        return screen;
    }

    public ScreenResponseDTO toDTO(Screen screen) {
        if ( screen == null ) {
            return null;
        }

        ScreenResponseDTO screenResponseDTO = new ScreenResponseDTO();

        screenResponseDTO.setScreenId(screen.getScreenId());
        screenResponseDTO.setScreenType(screen.getScreenType());
        screenResponseDTO.setTheater(screen.getTheater());
        screenResponseDTO.setCapacity(screen.getCapacity());
        screenResponseDTO.setNoOfRows(screen.getNoOfRows());
        screenResponseDTO.setCreatedAt(screen.getCreatedAt());
        screenResponseDTO.setCreatedBy(screen.getCreatedBy());
        screenResponseDTO.setSeats(seatMapper(screen.getSeats()));

        return screenResponseDTO;
    }
    public List<SeatResponseDTO> seatMapper(List<Seats> seats){
        List<SeatResponseDTO> seatRes= new ArrayList<>();
        for(Seats seat:seats){
            seatRes.add(SeatResponseDTO.builder()
                    .seatId(seat.getSeatId())
                    .name(seat.getName())
                    .screen(seat.getScreen())
                    .build());
        }
        return seatRes;
    }

}
