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
 student(id: "e1771576-d152-4afb-b665-3f17bf5edd96") {
        id
        name
        classNumber
    }
}

mutation createStudent{
  student(studentInput: {
    name: "Jhone Graph"
    classNumber: "A99234"
  }){
    id
    name
    classNumber
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
 deleteStudent(id: "c03f8043-d3fa-4084-b848-2f4076f5b4aa")
}
````

