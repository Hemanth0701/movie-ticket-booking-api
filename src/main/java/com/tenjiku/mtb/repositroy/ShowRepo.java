package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepo extends JpaRepository<Show,String> {
}
