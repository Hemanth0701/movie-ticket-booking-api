package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepo extends JpaRepository<Theater,String> {
   List<Theater>  findByName(String name);
}
