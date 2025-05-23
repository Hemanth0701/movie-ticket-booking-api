package com.tenjiku.mtb.dto.exit_dto.screen;

import com.tenjiku.mtb.entity.Movie;
import com.tenjiku.mtb.entity.Screen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponseDTO {

    private String showId;

    private Instant startsAt;

    private Instant endsAt;

    private Screen screen;

    private Movie movie;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;
}
