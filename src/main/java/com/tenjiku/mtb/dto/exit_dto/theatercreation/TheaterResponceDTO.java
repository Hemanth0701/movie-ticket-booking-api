package com.tenjiku.mtb.dto.exit_dto.theatercreation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TheaterResponceDTO {
    private String id;
    private String name;
    private String address;
    private String city;
    private String landmark;
    private LocalDateTime createdAt;
    private String createdBy;
}
