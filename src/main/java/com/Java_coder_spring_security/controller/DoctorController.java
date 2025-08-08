package com.Java_coder_spring_security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Java_coder_spring_security.dto.AppointmentResponseDto;
import com.Java_coder_spring_security.entity.User;
import com.Java_coder_spring_security.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId = userDetails.getId();
//        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(userId));
 
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(user.getId()));
    }

}
