package com.Java_coder_spring_security.repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.Java_coder_spring_security.dto.BloodGroupCountResponseEntity;
import com.Java_coder_spring_security.entity.Patient;
import com.Java_coder_spring_security.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;


public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("select new com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup," +
            " Count(p)) from Patient p group by p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);


    //    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();

}
