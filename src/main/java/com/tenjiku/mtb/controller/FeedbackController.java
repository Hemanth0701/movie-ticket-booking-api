package com.tenjiku.mtb.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class FeedbackController {
    private final FeedbackService feedbackService ;
}
