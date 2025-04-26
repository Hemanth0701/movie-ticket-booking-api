package com.tenjiku.mtb.controller;

import com.tenjiku.mtb.dto.entry_dto.user_registeration.LoginRequestDTO;
import com.tenjiku.mtb.dto.entry_dto.user_registeration.UserDetailsDTO;
import com.tenjiku.mtb.dto.exit_dto.user_registeration.UserDetailsResponseDTO;
import com.tenjiku.mtb.dto.update_dto.user_registeration.UserDetailsUpdateDTO;
import com.tenjiku.mtb.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody UserDetailsDTO userDetailsDTO){

        UserDetailsResponseDTO createdUser=userService.register(userDetailsDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(createdUser.getUsername())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);// if there is no any other instance

    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody @NotNull LoginRequestDTO loginRequest) {
        UserDetailsResponseDTO createdUser = userService.login(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestParam String id,
                                        @Valid @RequestBody UserDetailsUpdateDTO userDetailsUpdateDTO){
        UserDetailsResponseDTO updatedUser= userService.updateUser(id,userDetailsUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping(value = "/users/type")
    public ResponseEntity<Page<UserDetailsResponseDTO>> getUsersByType(@RequestParam String role,
                                                                       @RequestParam(defaultValue = "0")
                                                                       int page, @RequestParam(defaultValue = "10")
                                                                           int size) {
        System.out.println(role+" controller");
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<UserDetailsResponseDTO> userPage = userService.getUsersByType(role, pageable);
        return ResponseEntity.ok(userPage);
    }

}
