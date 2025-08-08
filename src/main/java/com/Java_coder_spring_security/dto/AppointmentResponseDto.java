package com.Java_coder_spring_security.dto;

import java.time.LocalDateTime;

import com.codingshuttle.youtube.hospitalManagement.dto.DoctorResponseDto;

import lombok.Data;

@Data
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private DoctorResponseDto doctor;
//    private PatientResponseDto patient;
}
