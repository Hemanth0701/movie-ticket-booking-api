package com.tenjiku.mtb.service;

import com.tenjiku.mtb.dto.entry_dto.UserDetailsDTO;
import com.tenjiku.mtb.dto.exit_dto.UserDetailsResponseDTO;
import com.tenjiku.mtb.dto.update_dto.UserDetailsUpdateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDetailsResponseDTO register(UserDetailsDTO userDetailsDTO);

    UserDetailsResponseDTO login(@NotBlank(message = "Username or email is required") String usernameOrEmail, @NotBlank(message = "Password is required") @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters") String password);

    UserDetailsResponseDTO updateUser(String id, UserDetailsUpdateDTO userDetailsUpdateDTO);

    Object deleteUser(String id);

    Page<UserDetailsResponseDTO> getUsersByType(String role, Pageable pageable);


}
