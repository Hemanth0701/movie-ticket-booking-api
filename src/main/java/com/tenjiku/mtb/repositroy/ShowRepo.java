package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepo extends JpaRepository<Show,String> {
}
