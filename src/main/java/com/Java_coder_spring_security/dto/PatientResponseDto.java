package com.Java_coder_spring_security.dto;


import java.time.LocalDate;

import com.Java_coder_spring_security.entity.type.BloodGroupType;

import lombok.Data;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}
