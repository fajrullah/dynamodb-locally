package com.example.datafetcher;

import com.example.entity.Student;
import com.example.model.StudentDto;
import com.example.model.StudentInput;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@DgsComponent
public class StudentDataFetcher {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @DgsData(parentType = "Query", field = "students")
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @DgsData(parentType = "Query", field = "student")
    public Student findOneStudent(@InputArgument("id") String id) {
        return studentService.findOne(id); // Replace with your logic to find a single student by ID
    }

    @DgsData(parentType = "Mutation", field = "student")
    public Student updateOrSaveStudent(@InputArgument("studentInput") Student student) {
        studentRepository.save(student);
        return student;
    }



//    @DgsMutation
//    public Customer customer(CustomerInput customerInput) {
//        Customer customer = Customer.builder()
//                .contact(customerInput.getContact())
//                .name(customerInput.getName())
//                .gender(customerInput.getGender())
//                .mail(customerInput.getMail())
//                .accounts(mapCustomerAccounts(customerInput.getAccounts()))
//                .build();
//        Customer customerResponse = customerRepository.save(customer);
//        return customerResponse;
//    }

}
