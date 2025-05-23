package com.tenjiku.mtb.service;

import com.tenjiku.mtb.dto.exit_dto.screen.ShowResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ShowService {
    ShowResponseDTO createShow(String theaterId, String screenId, String movieId, long startingTime);
}
