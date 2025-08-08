package com.Java_coder_spring_security.dto;

import lombok.Data;

@Data
public class OnboardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;
}
