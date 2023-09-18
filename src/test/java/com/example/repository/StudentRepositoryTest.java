package com.example.repository;

import com.example.entity.Student;
import com.example.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setId("1");
        student.setClassIds(null);

        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.save(student);

        verify(studentRepository, times(1)).save(student);
        assertEquals(student.getId(), savedStudent.getId(), "ID should match");
        assertSame(student, savedStudent, "References should be the same");
        assertNotNull(savedStudent, "Saved student should not be null");

    }

    @Test
    void testFindStudentById() {
        String entityId = "1";
        Student student = new Student();
        student.setId(entityId);

        when(studentRepository.findOne(entityId)).thenReturn(student);

        Student foundStudent = studentService.findOne(entityId);

        verify(studentRepository, times(1)).findOne(entityId);

        assertNotNull(foundStudent, "Found entity should not be null");
        assertEquals(student.getId(), foundStudent.getId(), "ID should match");
        assertSame(student, foundStudent, "References should not be the same");
        assertTrue(student.getId().equals(foundStudent.getId()), "ID should match");
        assertFalse(student.getId().isEmpty(), "ID should not be empty");
    }

    @Test
    void testDeleteStudent() {

        String studentIdToDelete = "1";
        String entityId = "1";
        Student student = new Student();
        student.setId(entityId);

        when(studentRepository.findOne(entityId)).thenReturn(student);

        when(studentRepository.findOne(studentIdToDelete)).thenReturn(student);

        studentService.delete(studentIdToDelete);

        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void testUpdateStudent() {
        String studentIdToUpdate = "1";

        Student existingStudent = new Student();
        existingStudent.setId("1");
        existingStudent.setName("Allia");
        studentRepository.save(existingStudent);

        Student updatedStudent = new Student();
        updatedStudent.setId("1");
        updatedStudent.setName("Allicia");

        when(studentRepository.findOne(studentIdToUpdate)).thenReturn(existingStudent);

        when(studentRepository.update(studentIdToUpdate, updatedStudent)).thenReturn(studentIdToUpdate);

        String result = studentService.update(updatedStudent);

        verify(studentRepository, times(1)).findOne(updatedStudent.getId());
        verify(studentRepository, times(1)).update(studentIdToUpdate, updatedStudent);

        assertNotNull(result, "Updated student should not be null");
        assertEquals(updatedStudent.getId(), result, "Student ID should match");
        assertEquals(updatedStudent.getName(), "Allicia", "Student name should match");
    }

    @Test
    void testFindAllStudents() {
        // Create a list of students
        List<Student> studentList = new ArrayList<>();

        Student firstStudent = new Student();
        firstStudent.setId("1");
        firstStudent.setName("Allia");

        Student secondStudent = new Student();
        secondStudent.setId("1");
        secondStudent.setName("Allicia");

        studentList.add(firstStudent);
        studentList.add(secondStudent);

        when(studentRepository.findAll()).thenReturn(studentList);

        List<Student> foundStudents = studentService.findAll();

        verify(studentRepository, times(1)).findAll();

        assertNotNull(foundStudents, "Found students list should not be null");
        assertEquals(studentList.size(), foundStudents.size(), "Number of students should match");
        for (int i = 0; i < studentList.size(); i++) {
            assertEquals(studentList.get(i).getId(), foundStudents.get(i).getId(),
                    "Student ID should match");
            assertEquals(studentList.get(i).getName(), foundStudents.get(i).getName(),
                    "Student name should match");
        }
    }


}
