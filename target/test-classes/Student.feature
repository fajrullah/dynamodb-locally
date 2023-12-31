Feature: Testing Student CRUD Management

  Background:
    Given there is a student with ID "1" in the repository

  Scenario: Save Student
    Given update with username "admin" and password "admin"
    When I call endpoint to save "/api/students" JSON body:
    """
      {
        "name": "John",
        "classNumber": "A100234",
         "classIds": null
      }
     """
    Then response status code is 201
    When I save the student with ID "1"
    Then the student with ID "1" is saved
    And response media type is application JSON

  Scenario: Find Student by ID
    Given a user with username "admin" and password "admin"
    When find the student with ID "1"
    Then the student with ID "1" is found
    When I call endpoint "/api/students/1"
    Then response status code is 200
    And response media type is not set

  Scenario: Delete Student by ID
    When I call delete endpoint "/api/students/1" with username "admin" and password "admin"
    Then response status code is 200
    When I delete the student with ID "1"
    Then the student with ID "1" is deleted
    And response after delete should be "Item not found for id: 1"


  Scenario: Update Student
    Given update with username "admin" and password "admin"
    When I call update endpoint "/api/students" JSON body:
     """
      {
        "id": "1",
        "name": "John",
        "classNumber": "A100234",
        "classIds": null
      }
     """
    Then response status code is 200
    When I update the student with ID "1" to have name "Allicia"
    Then the student with ID "1" is updated with name "Allicia"

  Scenario: Find All Students
    Given a user with username "admin" and password "admin"
    When it fetch all students
    Then the list of students should return students
    When I call endpoint "/api/students"
    Then response status code is 200
    And response media type is application JSON


    