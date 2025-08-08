package com.Java_coder_spring_security.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAppointmentRequestDto {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentTime;
    private String reason;
}
