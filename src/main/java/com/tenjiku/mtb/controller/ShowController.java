package com.tenjiku.mtb.controller;

import com.tenjiku.mtb.dto.exit_dto.screen.ShowResponseDTO;
import com.tenjiku.mtb.service.ShowService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@Validated
public class ShowController {

    private final ShowService showService;

    @PostMapping(value = "/theater/{theaterID}/screen/{screenId}/movie/{movieId}/addShow")
    public ResponseEntity<?> createShows(@PathVariable String theaterId, @PathVariable String screenId ,@PathVariable String movieId ,@NotNull long startingTime){

        ShowResponseDTO showResponse = showService.createShow(theaterId,screenId,movieId,startingTime);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{Id}")
                .buildAndExpand(showResponse.getShowId())
                .toUri();
        return ResponseEntity.created(location).body(showResponse);
    }
}
