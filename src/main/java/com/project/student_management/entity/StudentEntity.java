package com.project.student_management.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
public class StudentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
