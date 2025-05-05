package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepo extends JpaRepository<Screen,String> {

}
