package com.tenjiku.mtb.service.Impl;

import com.tenjiku.mtb.dto.entry_dto.theatercreation.TheaterDTO;
import com.tenjiku.mtb.dto.exit_dto.theatercreation.TheaterResponceDTO;
import com.tenjiku.mtb.dto.update_dto.theatercreation.TheaterUpdateDTO;
import com.tenjiku.mtb.entity.Theater;
import com.tenjiku.mtb.entity.TheaterOwner;
import com.tenjiku.mtb.exception.TheaterNotFoundException;
import com.tenjiku.mtb.exception.UserNotFoundException;
import com.tenjiku.mtb.mapper.TheaterEntityMapper;
import com.tenjiku.mtb.repositroy.TheaterRepo;
import com.tenjiku.mtb.repositroy.UserRepo;
import com.tenjiku.mtb.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TheaterServiceImp implements TheaterService {
    private final UserRepo userRepo;
    private final TheaterRepo theaterRepo;
    private final TheaterEntityMapper theaterMapper;

    @Override
    public TheaterResponceDTO register(String ownerId, TheaterDTO theaterDTO) {
        System.out.println("inbound Service :"+theaterDTO);
        // Step 1: Fetch the TheaterOwner or throw if not found
        TheaterOwner theaterOwner = (TheaterOwner) userRepo.findById(ownerId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + ownerId));
        System.out.println("theaterOwner without theater "+theaterOwner);
        // Step 2: Convert DTO to Entity and set ownership metadata
        Theater theater = theaterMapper.toEntity(theaterDTO);
        System.out.println("Convert DTO to Entity"+theater);
        theater.setCreatedBy(theaterOwner.getEmail());
        theater.setTheaterOwner(theaterOwner);
        System.out.println("theater Entity owner"+theater);
        // Step 3: Save theater entity
        Theater savedTheater = theaterRepo.save(theater);

        // Step 4: Update theaterOwner's list of theaters (if bidirectional)
        if (theaterOwner.getTheaters() == null) {
            theaterOwner.setTheaters(new ArrayList<>());
        }
        theaterOwner.getTheaters().add(savedTheater);
        userRepo.save(theaterOwner);
        System.out.println("theaterOwner with theater "+theaterOwner);
        // Step 5: Return the response DTO
        return theaterMapper.toDTO(savedTheater);
    }

    @Override
    public TheaterResponceDTO findTheaterById(String id) {
        return theaterMapper.toDTO(theaterRepo.findById(id).orElseThrow(() -> new TheaterNotFoundException("Theater not found with ID: " + id)));
    }

    @Override
    public List<TheaterResponceDTO> findTheaterByName(String name) {
        List<Theater> theaterList=theaterRepo.findByName(name);
        List<TheaterResponceDTO> theaterResponceDTOList=new ArrayList<TheaterResponceDTO>();
        for( Theater theater:theaterList){
            theaterResponceDTOList.add(theaterMapper.toDTO(theater));
        }
        return theaterResponceDTOList;
    }

    @Override
    public TheaterResponceDTO updateTheater(String id, TheaterUpdateDTO theaterDTO) {
        Theater existingTheater= theaterRepo.findById(id).orElseThrow(() -> new TheaterNotFoundException("Theater not found with ID: " + id));
        return theaterMapper.toDTO(theaterRepo.save(merge(existingTheater,theaterDTO)));
    }
    public Theater merge(Theater existingTheater, TheaterUpdateDTO theaterDTO){
        // Update fields — only if they're provided (null-safe)
        if (theaterDTO.getName()!= null) existingTheater.setName(theaterDTO.getName());
        if (theaterDTO.getAddress()!= null) existingTheater.setAddress(theaterDTO.getAddress());
        if (theaterDTO.getCity()!= null) existingTheater.setCity(theaterDTO.getCity());
        if (theaterDTO.getLandmark()!= null) existingTheater.setLandmark(theaterDTO.getLandmark());
        return existingTheater;
    }
}
