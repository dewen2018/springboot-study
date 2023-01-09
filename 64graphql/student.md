user请求
```graphql
mutation addStudent($input: StudentDTO!) {
    addStudent(input: $input) {
        code
        msg
    }
}

query findAll {
    findAll {
        id
        name
        age
    }
}

query findById {
    findById(id: 1) {
        id
        name
        age
    }
}

query findByName {
    findByName(name: "dewen") {
        id
        name
        age
    }
}

query findAllByPage {
    findAllByPage(pageNo: 1,pageSize:10) {
        items {
            id
            name
            age
        }
        totalCount
        pageSize
        pageNo
    }
}

```
Query Variables
```graphql
{
  "input": {
    "name": "dewen2号",
    "age": 18
  }
}
```