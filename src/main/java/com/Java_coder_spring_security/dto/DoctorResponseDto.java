package com.Java_coder_spring_security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDto {
    private Long id;
    private String name;
    private String specialization;
    private String email;
}
