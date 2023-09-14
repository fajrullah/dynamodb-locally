package com.example.datafetcher;

import com.example.entity.Class;
import com.example.entity.Student;
import com.example.repository.ClassRepository;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class ClassDataFetcher {

    @Autowired
    ClassRepository classRepository;

    @DgsData(parentType = "Query", field = "students")
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @DgsData(parentType = "Mutation", field = "class")
    public Class updateOrSave(@InputArgument("classInput") Class studentClass) {
        classRepository.save(studentClass);
        return studentClass;
    }

}
