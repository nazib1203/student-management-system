package com.project.student_management.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;

    @NotBlank(message = "Name can't be blank")
    @Size(min = 2, max = 25, message = "Name must be between 2 and 50 character")
    private String name;

    private Integer age;

    @Email(message = "please provide a valid email")
    private String email;
}