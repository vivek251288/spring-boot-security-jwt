package com.Java_coder_spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Java_coder_spring_security.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}