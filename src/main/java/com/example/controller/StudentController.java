package com.example.controller;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable(value = "id") String id){
        return studentService.findOne(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @PutMapping()
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        if (student.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Student ID is required");
        }
        String result = studentService.update(student);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id){
        try {
            String deleteStudent = studentService.delete(id);
            return ResponseEntity.ok(deleteStudent);
        } catch (AmazonDynamoDBException adb) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error delete data" + id);
        }
    }
}
