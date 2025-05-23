package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TheaterRepo extends JpaRepository<Theater,String> {
   List<Theater>  findByName(String name);
}
