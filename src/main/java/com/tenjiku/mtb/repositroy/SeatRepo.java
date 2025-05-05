package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seats,String> {
}
