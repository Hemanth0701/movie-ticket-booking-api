package com.tenjiku.mtb.mapper;

import com.tenjiku.mtb.dto.exit_dto.TheaterOwnerResponseDTO;
import com.tenjiku.mtb.dto.exit_dto.UserDetailsResponseDTO;
import com.tenjiku.mtb.dto.exit_dto.UserResponseDTO;
import com.tenjiku.mtb.entity.TheaterOwner;
import com.tenjiku.mtb.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsExitMapper {
    public UserResponseDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userdto = new UserResponseDTO();
        userdto.setId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setPhoneNumber(user.getPhoneNumber());
        userdto.setDob(user.getDob());
        userdto.setRole(String.valueOf(user.getRole()));
        userdto.setCreatedAt(user.getCreatedAt());
        return userdto;

    }
    public TheaterOwnerResponseDTO toTheaterOwnerDTO(TheaterOwner owner) {
        if ( owner == null ) {
            return null;
        }
        TheaterOwnerResponseDTO theaterOwnerDTO = new TheaterOwnerResponseDTO();
        theaterOwnerDTO.setId(owner.getId());
        theaterOwnerDTO.setUsername(owner.getUsername());
        theaterOwnerDTO.setEmail(owner.getEmail());
        theaterOwnerDTO.setPhoneNumber(owner.getPhoneNumber());
        theaterOwnerDTO.setDob(owner.getDob());
        theaterOwnerDTO.setRole(String.valueOf(owner.getRole()));
        theaterOwnerDTO.setCreatedAt(owner.getCreatedAt());

        return theaterOwnerDTO;
    }
    public UserDetailsResponseDTO toUserDetailsDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetailsResponseDTO.UserDetailsResponseDTOBuilder userDTO = UserDetailsResponseDTO.builder();
        userDTO.id(user.getId());
        userDTO.username(user.getUsername() );
        userDTO.email( user.getEmail()  );
        userDTO.phoneNumber( user.getPhoneNumber() );
        userDTO.Dob(user.getDob());
        userDTO.role( user.getRole().name() );
        userDTO.createdAt(user.getCreatedAt());

        return userDTO.build();
    }
    public UserDetailsResponseDTO toUserDetailsDTO(TheaterOwner owner) {
        if ( owner == null ) {
            return null;
        }

        UserDetailsResponseDTO.UserDetailsResponseDTOBuilder theaterOwnerDTO = UserDetailsResponseDTO.builder();
        theaterOwnerDTO.id( owner.getId() );
        theaterOwnerDTO.username( owner.getUsername() );
        theaterOwnerDTO.email( owner.getEmail() );
        theaterOwnerDTO.phoneNumber( owner.getPhoneNumber() );
        theaterOwnerDTO.role( owner.getRole().name() );
        theaterOwnerDTO.createdAt( owner.getCreatedAt() );

        return theaterOwnerDTO.build() ;
    }
}
