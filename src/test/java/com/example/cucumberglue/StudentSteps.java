package com.example.cucumberglue;

import com.example.DemoApplication;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = DemoApplication.class)
public class StudentSteps {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    private String studentId;
    private Student savedStudent;
    private Student existStudent;
    private Student updatedStudent;
    private List<Student> students;

    @Before
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
        student.setName("John");
        student.setClassNumber("A12344");
        when(studentRepository.save(student)).thenReturn(student);
        savedStudent = studentService.save(student);
        verify(studentRepository, times(1)).save(student);
        assertSame(student, savedStudent, "References should be the same");
    }
    @Then("the student with ID {string} is saved")
    public void theStudentWithIdIsSaved(String expectedStudentId) {
        assertEquals(expectedStudentId, savedStudent.getId(), "ID should match");
        assertNotNull(savedStudent, "Saved student should not be null");
    }
    @When("find the student with ID {string}")
    public void iFindTheStudentWithId(String studentId) {
        Student student = new Student();
        student.setId(studentId);
        when(studentRepository.findOne(studentId)).thenReturn(student);
        existStudent = studentService.findOne(student.getId());
        verify(studentRepository, times(1)).findOne(studentId);
        assertSame(student, existStudent, "References should not be the same");
        assertTrue(student.getId().equals(existStudent.getId()), "ID should match");
        assertFalse(student.getId().isEmpty(), "ID should not be empty");
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
        assertNotNull(studentId, "Updated student should not be null");
        assertEquals(updatedStudent.getId(), studentId, "Student ID should match");
        assertEquals(updatedStudent.getName(), "Allicia", "Student name should match");
    }
    @When("it fetch all students")
    public void iFetchAllStudents() {
        students = new ArrayList<>();
        Student student1 = new Student();
        student1.setId("1");
        student1.setName("John");
        Student student2 = new Student();
        student2.setId("2");
        student2.setName("Jane");
        students.add(student1);
        students.add(student2);

        when(studentRepository.findAll()).thenReturn(students);
    }
    @Then("the list of students should return students")
    public void theListOfStudentsShouldReturnStudents() {
        assertNotNull(students, "List of students should not be null");
        assertFalse(students.isEmpty(), "List of students should not be empty");
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> foundStudents = studentService.findAll();

        verify(studentRepository, times(1)).findAll();

        assertNotNull(foundStudents, "Found students list should not be null");
        assertEquals(students.size(), foundStudents.size(), "Number of students should match");
        for (int i = 0; i < students.size(); i++) {
            assertEquals(students.get(i).getId(), foundStudents.get(i).getId(),
                    "Student ID should match");
            assertEquals(students.get(i).getName(), foundStudents.get(i).getName(),
                    "Student name should match");
        }
    }

}
