# dynamodb-locally

````
query StudentsQuery {
  students {
    id
    name
    classNumber
  }
}

query StudentQuery {
 student(id: "e1771576-d152-4afb-b665-3f17bf5edd96") {
        id
        name
        classNumber
    }
}
````