package com.tenjiku.mtb.service.Impl;

import com.tenjiku.mtb.dto.entry_dto.user_registeration.TheaterOwnerDTO;
import com.tenjiku.mtb.dto.entry_dto.user_registeration.UserDTO;
import com.tenjiku.mtb.dto.entry_dto.user_registeration.UserDetailsDTO;
import com.tenjiku.mtb.dto.exit_dto.user_registeration.UserDetailsResponseDTO;
import com.tenjiku.mtb.dto.update_dto.user_registeration.TheaterOwnerUpdateDTO;
import com.tenjiku.mtb.dto.update_dto.user_registeration.UserDetailsUpdateDTO;
import com.tenjiku.mtb.dto.update_dto.user_registeration.UserUpdateDTO;
import com.tenjiku.mtb.entity.TheaterOwner;
import com.tenjiku.mtb.entity.User;
import com.tenjiku.mtb.entity.UserDetails;
import com.tenjiku.mtb.exception.*;
import com.tenjiku.mtb.mapper.UserDetailsEntryMapper;
import com.tenjiku.mtb.mapper.UserDetailsExitMapper;
import com.tenjiku.mtb.repositroy.UserRepo;
import com.tenjiku.mtb.service.RoleService;
import com.tenjiku.mtb.service.UserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private final UserDetailsEntryMapper userDetailsEntryMapper;
    private final UserDetailsExitMapper userDetailsExitMapper;

    @Override
    public UserDetailsResponseDTO register(@NotNull UserDetailsDTO dto) {
        //  check if  an email or username already exist
        if (userRepo.existsByUsername(dto.getUsername()) || userRepo.existsByEmail(dto.getEmail())) {
            throw new UsernameAlreadyExistsException("Username or email already exists");
        } else {
            if (dto instanceof TheaterOwnerDTO) {
                TheaterOwner owner = userDetailsEntryMapper.toTheaterOwner((TheaterOwnerDTO) dto);
                TheaterOwner saved = userRepo.save(owner);
                return userDetailsExitMapper.toTheaterOwnerDTO(saved);
            } else if (dto instanceof UserDTO) {
                User user = userDetailsEntryMapper.toUser((UserDTO) dto);
                User saved = userRepo.save(user);
                return userDetailsExitMapper.toUserDTO(saved);
            } else
                throw new InternalServerErrorException(" Server Went Down ");
        }
    }

    @Override
    public UserDetailsResponseDTO login(String usernameOrEmail, String password) {
        // Perform a single lookup query to check if it's an email or username
        Optional<UserDetails> userDetailsOpt = userRepo.loginByUsernameOrEmail(usernameOrEmail);

        if (userDetailsOpt.isEmpty()) {
            throw new UsernameNotFoundException("Username or email not found");
        }

        UserDetails userDetails = userDetailsOpt.get(); //convert to userDetails instance

        if (!password.equals(userDetails.getPassword()) ) { //check Password
            throw new InvalidPasswordException(" Username and password do not match");
        }
        else{
        // Return appropriate DTO based on the user type
            if (userDetails instanceof User)
                return userDetailsExitMapper.toUserDTO((User) userDetails);
            else if (userDetails instanceof TheaterOwner)
                return userDetailsExitMapper.toTheaterOwnerDTO((TheaterOwner) userDetails);
            else
                throw new IllegalStateException("Unexpected user type");}
    }

    @Override
    public UserDetailsResponseDTO updateUser(String id,  UserDetailsUpdateDTO updatedUserDTO) {

        UserDetails existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (updatedUserDTO instanceof TheaterOwnerUpdateDTO) {
            TheaterOwner owner = userDetailsEntryMapper.toTheaterOwner((TheaterOwnerUpdateDTO)  updatedUserDTO);
            return userDetailsExitMapper.toTheaterOwnerDTO((TheaterOwner) userRepo.save(mergeUserDetails(existingUser,owner)));
        } else if (updatedUserDTO instanceof UserUpdateDTO) {
            User user = userDetailsEntryMapper.toUser((UserUpdateDTO)  updatedUserDTO);
            return userDetailsExitMapper.toUserDTO((User) userRepo.save(mergeUserDetails(existingUser,user)));
        } else
            throw new InternalServerErrorException(" Server Went Down ");
    }

    public UserDetails mergeUserDetails(UserDetails existingUser, @NotNull UserDetails updatedUser){
        // Update fields — only if they're provided (null-safe)
        if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPhoneNumber() != null) existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getDob() != null) existingUser.setDob(updatedUser.getDob());
        return existingUser;
    }

    @Override
    public String deleteUser(String id) {
        UserDetails user = userRepo.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("User not found with ID: " + id));
        if (user.isDeleted()) {
            throw new UserAlreadyDeletedException("User is already deleted.");

        }
        user.setDeleted(true);
        user.setDeletedAt(Instant.now());
        userRepo.save(user);

        return "Deleted successfully";
    }

    @Override
    public Page<UserDetailsResponseDTO> getUsersByType(@NotNull String role, Pageable pageable) {

        Page<UserDetails> users = userRepo.findAllByRole(RoleService.fromString(role) , pageable);

        return users.map(user -> {
            if (user instanceof User) {
                return userDetailsExitMapper.toUserDetailsDTO((User) user);
            } else if (user instanceof TheaterOwner) {
                return userDetailsExitMapper.toUserDetailsDTO((TheaterOwner) user);
            } else {
                throw new IllegalStateException("Unknown user type: " + user.getClass().getSimpleName());
            }
        });
    }

}
