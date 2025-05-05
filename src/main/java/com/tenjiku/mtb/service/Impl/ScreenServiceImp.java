package com.tenjiku.mtb.service.Impl;

import com.tenjiku.mtb.dto.entry_dto.screen.ScreenDTO;
import com.tenjiku.mtb.dto.exit_dto.screen.ScreenResponseDTO;
import com.tenjiku.mtb.entity.Screen;
import com.tenjiku.mtb.entity.Seats;
import com.tenjiku.mtb.entity.Theater;
import com.tenjiku.mtb.exception.NoOfRowsExceedCapacityException;
import com.tenjiku.mtb.exception.ScreenNotFoundException;
import com.tenjiku.mtb.exception.TheaterNotFoundException;
import com.tenjiku.mtb.mapper.ScreenMapper;
import com.tenjiku.mtb.repositroy.ScreenRepo;
import com.tenjiku.mtb.repositroy.SeatRepo;
import com.tenjiku.mtb.repositroy.TheaterRepo;
import com.tenjiku.mtb.service.ScreenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScreenServiceImp implements ScreenService {

    private final TheaterRepo theaterRepo;
    private final ScreenRepo screenRepo;
    private final SeatRepo seatRepo;
    private final ScreenMapper screenMapper;

    @Override
    public ScreenResponseDTO createScreen(String theaterId, ScreenDTO screenDTO) {
        return screenMapper.toDTO(screenRepo.save(merge(screenMapper.toEntity(screenDTO),theaterRepo.findById(theaterId).orElseThrow(()-> new TheaterNotFoundException(" Theater not found with this Id :"+theaterId)))));
    }

    @Override
    public ScreenResponseDTO findScreenById(String theaterId, String screenId) {
        if (theaterRepo.existsById(theaterId)) {
            if (screenRepo.existsById(screenId))
                return screenMapper.toDTO(screenRepo.findById(screenId).orElseThrow(()-> new ScreenNotFoundException(" no Screen exist with this id : "+screenId)));
        }
        throw new TheaterNotFoundException("Theater not found by Id");

    }

    private Screen merge(Screen screen,Theater theater){
        screen.setTheater(theater);
        if (screen.getNoOfRows() > screen.getCapacity())
            throw new NoOfRowsExceedCapacityException("The no.of rows exceed the capacity");
        screen.setSeats(generateSeats(screen));
        screen.setCreatedBy(theater.getCreatedBy());
        return  screen;
    }
    private List<Seats> generateSeats(Screen screen ){
        List<Seats> seats = new ArrayList<>();
        int noOfSeatsPerRow = screen.getCapacity() / screen.getNoOfRows();
        char row = 'A';
        for (int i = 1, j = 1; i <= screen.getCapacity(); i++, j++) {
            Seats seat = new Seats();
            seat.setScreen(screen);
            seat.setDelete(false);
            seat.setName(row + "" + j);
            seatRepo.save(seat);
            seats.add(seat);
            if (j == noOfSeatsPerRow) {
                j = 0;
                row++;
            }
        }
        return seats;
    }
}
