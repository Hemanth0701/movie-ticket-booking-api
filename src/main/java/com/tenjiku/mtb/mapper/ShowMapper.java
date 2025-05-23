package com.tenjiku.mtb.mapper;

import com.tenjiku.mtb.dto.exit_dto.screen.ScreenResponseDTO;
import com.tenjiku.mtb.dto.exit_dto.screen.ShowResponseDTO;
import com.tenjiku.mtb.entity.Show;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {

    public ShowResponseDTO toDTO(Show show) {
        if ( show == null ) {
            return null;
        }
        return ShowResponseDTO.builder()
                .showId(show.getShowId())
                .movie(show.getMovie())
                .screen(show.getScreen())
                .startsAt(show.getStartsAt())
                .endsAt(show.getEndsAt())
                .createdAt(show.getCreatedAt())
                .updatedAt(show.getUpdatedAt())
                .createdBy(show.getCreatedBy())
                .build();
    }
}
