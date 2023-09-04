package com.example.controller;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") String id){
        return studentService.findOne(id);
    }
    @PostMapping()
    public Student createStudent(@RequestBody Student student){
        return studentService.save(student);
    }
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable(value = "id") String id, @RequestBody Student student){
        return studentService.update(id, student);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") String id){
        return studentService.delete(id);
    }
}
