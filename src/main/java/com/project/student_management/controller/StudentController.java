package com.project.student_management.controller;
import com.project.student_management.dto.StudentDTO;
import com.project.student_management.entity.StudentEntity;
import com.project.student_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/student")
public class StudentController{
    private final StudentService service;
    public StudentController(StudentService service){
        this.service = service;
    }
    //create
    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO dto){
        return service.createStudent(dto);
    }
    //ReadAll
    @GetMapping
    public List <StudentDTO> getAllStudents(){
        return service.getAllStudents();
    }
    //ReadById
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Integer id){
        return service.getStudentById(id);
    }
    //update
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentDTO dto){
        return service.updateStudent(id,dto);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteStudent(@PathVariable Integer id){
        service.deleteStudent(id);
        return ResponseEntity.ok("Student Deleted Successfully");
    }
    //search
    @GetMapping("/search")
    public List<StudentDTO> searchByName(@RequestParam String name){
        return service.searchByName(name);
    }
    // PAGINATION + SORTING
    @GetMapping("/page")
    public Page<StudentDTO> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return service.getStudent(
                page,
                size,
                sortBy
        );
    }
}