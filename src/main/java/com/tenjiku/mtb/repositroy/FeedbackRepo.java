package com.tenjiku.mtb.repositroy;

import com.tenjiku.mtb.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback,String> {
}
