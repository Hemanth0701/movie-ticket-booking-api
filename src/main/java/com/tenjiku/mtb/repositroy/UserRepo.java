package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.UserDetails;
import com.tenjiku.mtb.entity.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDetails,String> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM UserDetails u WHERE (u.email = :input OR u.username = :input) AND u.isDeleted = false")
    Optional<UserDetails> loginByUsernameOrEmail(@Param("input") String usernameOrEmail);


    Page<UserDetails> findAllByRole(Role role, Pageable pageable);
}
