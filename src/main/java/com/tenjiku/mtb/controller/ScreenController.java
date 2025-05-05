package com.tenjiku.mtb.controller;

import com.tenjiku.mtb.dto.entry_dto.screen.ScreenDTO;
import com.tenjiku.mtb.dto.exit_dto.screen.ScreenResponseDTO;
import com.tenjiku.mtb.service.ScreenService;
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
public class ScreenController {

   private final ScreenService screenService;

    @PostMapping(value = "/addScreen")
    public ResponseEntity<?> createScreen(@RequestParam String theaterId,@Valid @RequestBody ScreenDTO screenDTO){
        ScreenResponseDTO screen =screenService.createScreen(theaterId,screenDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{Id}")
                .buildAndExpand(screen.getScreenId())
                .toUri();
        return ResponseEntity.created(location).body(screen);
    }

    @GetMapping(value = "/theater/{theaterId}/screen/{screenId}")
    public  ResponseEntity<?> findScreenById(@PathVariable String theaterId,@PathVariable String ScreenId){
        return ResponseEntity.ok(screenService.findScreenById(theaterId,ScreenId));
    }
}
