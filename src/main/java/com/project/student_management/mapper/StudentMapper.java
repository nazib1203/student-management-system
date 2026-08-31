package com.project.student_management.mapper;

import com.project.student_management.dto.StudentDTO;
import com.project.student_management.entity.StudentEntity;

public class StudentMapper {

    public static StudentDTO toDTO(StudentEntity student) {
        if (student == null) {
            return null;
        }
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .email(student.getEmail())
                .build();
    }

    public static StudentEntity toEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }
        StudentEntity student = new StudentEntity();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        return student;
    }
}