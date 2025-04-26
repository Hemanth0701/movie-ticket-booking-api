package com.tenjiku.mtb.mapper;

import com.tenjiku.mtb.dto.entry_dto.theatercreation.TheaterDTO;
import com.tenjiku.mtb.dto.exit_dto.theatercreation.TheaterResponceDTO;
import com.tenjiku.mtb.dto.update_dto.theatercreation.TheaterUpdateDTO;
import com.tenjiku.mtb.entity.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterEntityMapper {
    public Theater toEntity(TheaterDTO dto) {
        if ( dto == null ) {
            return null;
        }
          Theater theater = new Theater();
       theater.setName(dto.getName());
       theater.setCity(dto.getCity());
       theater.setLandmark(dto.getLandmark());
       theater.setAddress(dto.getAddress());
        return theater;
    }

    public Theater toEntity(TheaterUpdateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Theater theater = new Theater();
        theater.setName(dto.getName());
        theater.setCity(dto.getCity());
        theater.setLandmark(dto.getLandmark());
        theater.setAddress(dto.getAddress());
        return theater;
    }


    public TheaterResponceDTO toDTO(Theater savedTheater) {
        if ( savedTheater == null ) {
            return null;
        }
        TheaterResponceDTO theaterResponceDTO=new TheaterResponceDTO();
        theaterResponceDTO.setId(savedTheater.getId());
        theaterResponceDTO.setName(savedTheater.getName());
        theaterResponceDTO.setAddress(savedTheater.getAddress());
        theaterResponceDTO.setCity(savedTheater.getCity());
        theaterResponceDTO.setLandmark(savedTheater.getLandmark());
        theaterResponceDTO.setCreatedAt(savedTheater.getCreatedAt());
        theaterResponceDTO.setCreatedBy(savedTheater.getCreatedBy());

        return theaterResponceDTO;
    }
}
