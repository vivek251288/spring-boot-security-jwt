package com.Java_coder_spring_security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Java_coder_spring_security.dto.DoctorResponseDto;
import com.Java_coder_spring_security.dto.OnboardDoctorRequestDto;
import com.Java_coder_spring_security.entity.Doctor;
import com.Java_coder_spring_security.entity.User;
import com.Java_coder_spring_security.entity.type.RoleType;
import com.Java_coder_spring_security.repository.DoctorRepository;
import com.Java_coder_spring_security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnboardDoctorRequestDto onBoardDoctorRequestDto) {
        User user = userRepository.findById(onBoardDoctorRequestDto.getUserId()).orElseThrow();

        if(doctorRepository.existsById(onBoardDoctorRequestDto.getUserId())) {
            throw new IllegalArgumentException("Already a doctor");
        }

        Doctor doctor = Doctor.builder()
                .name(onBoardDoctorRequestDto.getName())
                .specialization(onBoardDoctorRequestDto.getSpecialization())
                .user(user)
                .build();

        user.getRoles().add(RoleType.DOCTOR);

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}
