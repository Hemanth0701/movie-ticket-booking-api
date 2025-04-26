package com.tenjiku.mtb.service;

import com.tenjiku.mtb.dto.entry_dto.theatercreation.TheaterDTO;
import com.tenjiku.mtb.dto.exit_dto.theatercreation.TheaterResponceDTO;
import com.tenjiku.mtb.dto.update_dto.theatercreation.TheaterUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TheaterService {
    TheaterResponceDTO register(String id, TheaterDTO theaterDTO);

    TheaterResponceDTO findTheaterById(String id);

   List<TheaterResponceDTO>  findTheaterByName(String name);

    TheaterResponceDTO updateTheater(String id, TheaterUpdateDTO theaterDTO);
}
