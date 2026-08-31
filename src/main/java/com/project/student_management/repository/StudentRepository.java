package com.project.student_management.repository;

import com.project.student_management.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends
     JpaRepository<StudentEntity, Integer>

    {
        List<StudentEntity> findByName(String name);
        List<StudentEntity> findByAgeGreaterThan(Integer age);
    }
