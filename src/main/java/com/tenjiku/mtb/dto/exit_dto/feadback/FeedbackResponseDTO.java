package com.tenjiku.mtb.dto.exit_dto.feadback;

import com.tenjiku.mtb.entity.Movie;
import com.tenjiku.mtb.entity.User;
import java.time.Instant;

public class FeedbackResponseDTO {

        private String feedbackId;

        private float rating;

        private String review;

        private User user;

        private Movie movie;

        private Instant createdAt;

}

