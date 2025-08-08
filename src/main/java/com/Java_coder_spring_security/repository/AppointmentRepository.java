package com.Java_coder_spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Java_coder_spring_security.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}