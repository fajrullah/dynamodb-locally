# ASSIGNMENT

- AWS Dynamodb
- GRAPHQL (DGS) NETFLIX
- CUCUMBER
- OPA (Open Policy Agent)

````
query studentsQuery{
  students {
    id
    name
    classNumber
  }
}
query studentQuery{
 student(id: "someid") {
        id
        name
        classNumber
    }
}
mutation createStudent{
  student(studentInput: {
    name: "Jhone Graph"
    classNumber: "A99234"
    classIds: ["1"]
  }){
    id
    name
    classNumber
    classIds
  }
}
mutation updateStudent{
  student(studentInput: {
    id: "someID"
    name: "Jhone Graph"
    classNumber: "A99234"
  }){
    id
    name
    classNumber
  }
}

mutation deleteStudent {
 deleteStudent(id: "someid")
}
````
````
mutation studentRegisterClass{
  studentRegisterClass(registerInput: {
    classId: "fc3e674d-ea63-4817-a67a-bde27f21fd4e"
    studentId: "67ba8839-978e-445a-9c0e-430db0896921"
  }){
    id
    name
    classes{
      className
    }
  }
}
query allClassesWithStudent{
  allClassesWithStudents{
    id
    className
   	students{
      id
      name
    }
  }
}
query allStudentsWithClass{
  allStudentsWithClasses{
    id
    name
    classes{
      id
      className
    }
  }
}
````
````
query classesQuery{
  classes{
    id
    className
  }
}
query classQuery{
  classes(id: "someid"){
    id
    className
  }
}
mutation createClass{
  class(classInput: {
    className: "Math"
    studentIds: ["1"]
  }){
    id
    className
    studentIds
  }
}
````

