package com.project.student_management.service;
import com.project.student_management.dto.StudentDTO;
import com.project.student_management.entity.StudentEntity;
import com.project.student_management.exception.StudentNotFoundException;
import com.project.student_management.mapper.StudentMapper;
import com.project.student_management.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service

public class StudentService{
    private final StudentRepository repository;
    public StudentService(StudentRepository repository){
        this.repository = repository;
    }
    //CREATE
    public StudentDTO createStudent(StudentDTO dto){
        log.info("creating student");
        StudentEntity student = StudentMapper.toEntity(dto);
        StudentEntity saveStudent = repository.save(student);
        log.info("student created with id: {}", saveStudent.getId());
        return StudentMapper.toDTO(saveStudent);
    }
    //READ ALL
    public List<StudentDTO> getAllStudents(){
        log.info("Fetching data");
        return repository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .toList();
    }
    //READ BY ID
    public StudentDTO getStudentById(Integer Id){
        log.info("fetching student with id: {}", Id);
        StudentEntity student = repository.findById(Id).orElseThrow(()->{
            log.error("Student not found with id: {}",Id);
        return new StudentNotFoundException(
                "Student not found with id: " + Id
        );
    });

        return StudentMapper.toDTO(student);
    }
    //update
    public StudentDTO updateStudent(Integer id, StudentDTO dto){
        StudentEntity existingStudent = repository.findById(id).orElseThrow(()->
                new StudentNotFoundException(
                "Student not found with id: " + id
        ));
        existingStudent.setName(dto.getName());
        existingStudent.setAge(dto.getAge());
        existingStudent.setEmail(dto.getEmail());
        StudentEntity updateStudent = repository.save(existingStudent);
        return StudentMapper.toDTO(updateStudent);
    }
    //Delete
    public void deleteStudent(Integer id){
        StudentEntity student = repository.findById(id).orElseThrow(()->
                new StudentNotFoundException(
                        "Student not found with this id: " + id

                ));
        repository.delete(student);
        log.info("Student deleted successfully with id: " + id);
    }
    //search by name
    public List<StudentDTO> searchByName(String name){
        return repository.findByName(name)
                .stream()
                .map(StudentMapper::toDTO)
                .toList();

    }
        //pagination + sorting
    public Page<StudentDTO> getStudent(int page, int size,String sortBy){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).ascending()
        );
        return repository.findAll(pageable).map(StudentMapper::toDTO);
    }
    }
