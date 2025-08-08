package com.Java_coder_spring_security.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Java_coder_spring_security.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}