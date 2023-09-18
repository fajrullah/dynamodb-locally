package com.example.datafetcher;

import com.example.entity.Class;
import com.example.entity.Student;
import com.example.model.ClassInput;
import com.example.repository.ClassRepository;
import com.example.repository.StudentRepository;
import com.example.service.ClassService;
import com.example.service.StudentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class ClassDataFetcher {

    @Autowired
    ClassService classService;

    @DgsData(parentType = "Query", field = "classes")
    public List<Class> findAll() {
        return classService.findAll();
    }

    @DgsData(parentType = "Query", field = "class")
    public Class findOneStudent(@InputArgument("id") String id) {
        return classService.findOne(id);
    }

    @DgsData(parentType = "Query", field = "allClassesWithStudents")
    public List<Class> getAllStudentsWithClasses() {
        return classService.getAllClassesWithStudents();
    }

    @DgsData(parentType = "Mutation", field = "updateClass")
    public Class update(@InputArgument("classInput") ClassInput studentClass) {
        return classService.saveClass(studentClass);
    }
    @DgsData(parentType = "Mutation", field = "createClass")
    public Class save(@InputArgument("classInput") ClassInput studentClass) {
        return classService.saveClass(studentClass);
    }

    @DgsData(parentType = "Mutation", field = "deleteClass")
    public String deleteClass(@InputArgument("id") String id) {
        try{
            classService.delete(id);
            return id;
        } catch(Exception e) {
            return null;
        }
    }
}
