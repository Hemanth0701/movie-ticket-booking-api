package com.tenjiku.mtb.service;

import com.tenjiku.mtb.dto.entry_dto.screen.ScreenDTO;
import com.tenjiku.mtb.dto.exit_dto.screen.ScreenResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ScreenService {

    ScreenResponseDTO createScreen(String theaterId, ScreenDTO screenDTO);

    ScreenResponseDTO findScreenById(String theaterId, String screenId);
}
