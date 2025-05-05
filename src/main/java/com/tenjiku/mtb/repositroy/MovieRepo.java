package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,String> {
}
