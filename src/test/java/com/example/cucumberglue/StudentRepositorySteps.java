package com.example.cucumberglue;

import com.example.DemoApplication;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = DemoApplication.class)
public class StudentRepositorySteps {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    private String studentId;
    private Student savedStudent;
    private Student existStudent;
    private Student updatedStudent;

    @Before // Add a @Before method
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }
    @Given("there is a student with ID {string} in the repository")
    public void thereIsAStudentWithIdInTheRepository(String studentId) {
        this.studentId = studentId;
    }
    @When("I save the student with ID {string}")
    public void iSaveTheStudentWithId(String studentId) {
        Student student = new Student();
        student.setId(studentId);
        savedStudent = studentService.save(student);
    }
    @Then("the student with ID {string} is saved")
    public void theStudentWithIdIsSaved(String expectedStudentId) {
        assertNotNull(savedStudent);
        assertEquals(expectedStudentId, savedStudent.getId(), "ID should match");
        assertNotNull(savedStudent, "Saved student should not be null");
    }
    @When("I find the student with ID {string}")
    public void iFindTheStudentWithId(String studentId) {
        Student student = new Student();
        student.setId(studentId);
        existStudent = studentService.findOne(student.getId());
    }
    @Then("the student with ID {string} is found")
    public void theStudentWithIdIsFound(String expectedStudentId) {
        assertNotNull(existStudent);
        assertEquals(expectedStudentId, existStudent.getId(), "ID should match");
    }
    @When("I delete the student with ID {string}")
    public void iDeleteheStudentWithId(String id) {
        Student student = new Student();
        student.setId(id);
        studentId = studentService.delete(id);
    }
    @Then("the student with ID {string} is deleted")
    public void theStudentWithIdIsDeleted(String expectedStudentId) {
        assertNotNull(studentId);
        assertNull(studentService.findOne(studentId), "Student should not exist after deletion");
    }

    @When("I update the student with ID {string} to have name {string}")
    public void iUpdateTheStudentWithIdToHaveName(String studentId, String updatedName) {
        updatedStudent = new Student();
        updatedStudent.setId(studentId);
        updatedStudent.setName(updatedName);
        studentId = studentService.update(updatedStudent);
    }

    @Then("the student with ID {string} is updated with name {string}")
    public void theStudentWithIdIsUpdatedWithName(String expectedStudentId, String expectedName) {
        assertNotNull(studentId, "Updated student should not be null");
        assertEquals(expectedStudentId, updatedStudent.getId(), "Student ID should match");
        assertEquals(expectedName, updatedStudent.getName(), "Student name should match");
        when(studentRepository.findOne(studentId)).thenReturn(updatedStudent);
        when(studentRepository.update(studentId, updatedStudent)).thenReturn(studentId);
        verify(studentRepository, times(1)).findOne(studentId);
    }

}
