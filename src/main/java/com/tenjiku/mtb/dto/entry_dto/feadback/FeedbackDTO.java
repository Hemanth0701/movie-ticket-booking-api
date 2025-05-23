package com.tenjiku.mtb.dto.entry_dto.feadback;

import com.tenjiku.mtb.entity.Movie;
import com.tenjiku.mtb.entity.User;
import jakarta.validation.constraints.*;

public class FeedbackDTO {

        @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0")
        @DecimalMax(value = "5.0", inclusive = true, message = "Rating must be at most 5.0")
        private float rating;

        @NotBlank(message = "Review cannot be blank")
        @Size(max = 1000, message = "Review cannot exceed 1000 characters")
        private String review;

        @NotNull(message = "User is required")
        private User user;

        @NotNull(message = "Movie is required")
        private Movie movie;
}

