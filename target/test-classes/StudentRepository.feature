Feature: Testing Student Repository Operations

  Background:
    Given there is a student with ID "1" in the repository

  Scenario: Save Student
    When I save the student with ID "1"
    Then the student with ID "1" is saved

  Scenario: Find Student by ID
    When I find the student with ID "1"
    Then the student with ID "1" is found

  Scenario: Delete Student by ID
    When I delete the student with ID "1"
    Then the student with ID "1" is deleted

  Scenario: Update Student
    When I update the student with ID "1" to have name "Allicia"
    Then the student with ID "1" is updated with name "Allicia"

  Scenario: Find All Students
    When I fetch all students
    Then the list of students should contain "Allia" and "Allicia"
