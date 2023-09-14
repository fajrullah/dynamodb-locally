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

