package com.tenjiku.mtb.mapper;

import com.tenjiku.mtb.dto.entry_dto.TheaterOwnerDTO;
import com.tenjiku.mtb.dto.entry_dto.UserDTO;
import com.tenjiku.mtb.entity.TheaterOwner;
import com.tenjiku.mtb.entity.User;
import com.tenjiku.mtb.entity.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsEntryMapper {

    public User toUser(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDob(dto.getDob());
        user.setRole(Role.USER);
        return user;
    }

    public TheaterOwner toTheaterOwner(TheaterOwnerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TheaterOwner owner = new TheaterOwner();
        owner.setUsername(dto.getUsername());
        owner.setEmail(dto.getEmail());
        owner.setPassword(dto.getPassword());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setDob(dto.getDob());
        owner.setRole(Role.THEATER_OWENER);

        return owner;
    }
}
