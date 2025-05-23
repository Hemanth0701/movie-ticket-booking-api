package com.tenjiku.mtb.service.Impl;

import com.tenjiku.mtb.dto.exit_dto.screen.ShowResponseDTO;
import com.tenjiku.mtb.entity.Movie;
import com.tenjiku.mtb.entity.Screen;
import com.tenjiku.mtb.entity.Show;
import com.tenjiku.mtb.entity.Theater;
import com.tenjiku.mtb.exception.MovieNotFoundException;
import com.tenjiku.mtb.exception.ScreenNotFoundException;
import com.tenjiku.mtb.exception.ShowTimeConflictException;
import com.tenjiku.mtb.exception.TheaterNotFoundException;
import com.tenjiku.mtb.mapper.ShowMapper;
import com.tenjiku.mtb.repositroy.MovieRepo;
import com.tenjiku.mtb.repositroy.ScreenRepo;
import com.tenjiku.mtb.repositroy.ShowRepo;
import com.tenjiku.mtb.repositroy.TheaterRepo;
import com.tenjiku.mtb.service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShowServiceImp implements ShowService {

    private final TheaterRepo theaterRepo;
    private final ScreenRepo screenRepo;
    private final MovieRepo movieRepo;
    private final ShowRepo showRepo;
    private final ShowMapper showMapper;


    @Override
    public ShowResponseDTO createShow(String theaterId, String screenId, String movieId, long startingTime) {

        // Step 1: Validate existence
        Theater theater = theaterRepo.findById(theaterId)
                .orElseThrow(() -> new TheaterNotFoundException("Theater ID not found: " + theaterId));

        Screen screen = screenRepo.findById(screenId)
                .orElseThrow(() -> new ScreenNotFoundException("Screen ID not found: " + screenId));
        Set<Show> screenShows= (screen.getShows()==null)?new HashSet<>():screen.getShows();

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie ID not found: " + movieId));
        List<Show> movieShow =(movie.getShows()==null)?new ArrayList<>():movie.getShows();

        // Step 2: Conflict check
        Instant newStartTime = Instant.ofEpochMilli(startingTime);
        Instant newEndTime = newStartTime.plus(movie.getRuntime());

        boolean conflictExists = screen.getShows().stream()
                .anyMatch(existingShow -> {
                    Instant existingStart = existingShow.getStartsAt();
                    Instant existingEnd = existingShow.getEndsAt();
                    return !(newEndTime.isBefore(existingStart) || newStartTime.isAfter(existingEnd));
                });

        if (conflictExists) {
            throw new ShowTimeConflictException("Another show is already scheduled during this time slot.");
        }
        // Step 3: Create and persist the show
        Show show = Show.builder()
                .movie(movie)
                .screen(screen)
                .startsAt(newStartTime)
                .endsAt(newEndTime)
                .createdBy(theater.getCreatedBy())
                .build();

        showRepo.save(show);

        screenShows.add(show);
        screen.setShows(screenShows);
        screenRepo.save(screen);

        movieShow.add(show);
        movie.setShows(movieShow);
        movieRepo.save(movie);

        // Step 4: Map to DTO and return
        return showMapper.toDTO(show);
    }
}
