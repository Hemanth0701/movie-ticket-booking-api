package com.tenjiku.mtb.controller;

import com.tenjiku.mtb.dto.entry_dto.theatercreation.TheaterDTO;
import com.tenjiku.mtb.dto.exit_dto.theatercreation.TheaterResponceDTO;
import com.tenjiku.mtb.dto.update_dto.theatercreation.TheaterUpdateDTO;
import com.tenjiku.mtb.service.TheaterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@Validated
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping(value = "/theaters")
    public ResponseEntity<?> userRegister( @RequestParam String id, @Valid @RequestBody TheaterDTO theaterDTO){

        TheaterResponceDTO createdTheater=theaterService.register(id,theaterDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(createdTheater.getName())
                .toUri();
        return ResponseEntity.created(location).body(createdTheater);// if there is no any other instance
    }
    @GetMapping(value = "/theater/{id}")
    public ResponseEntity<?> findTheaterById(@PathVariable String id){
        return ResponseEntity.ok(theaterService.findTheaterById(id));
    }

    @GetMapping(value = "/theaters/{name}")
    public ResponseEntity<?> findTheaterByName(@PathVariable String name){
        return ResponseEntity.ok(theaterService.findTheaterByName(name));
    }

    @PutMapping(value = "/updates")
    public ResponseEntity<?> updateTheater(@RequestParam String id, @Valid @RequestBody TheaterUpdateDTO theaterUpdateDTO){
        return ResponseEntity.ok(theaterService.updateTheater(id,theaterUpdateDTO));
    }

}
