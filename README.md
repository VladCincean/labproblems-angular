# Lab problems
A teacher manages information about _students_ and _lab problems_.
Create an application which allows to:
- perform CRUD operations on _students_ and _lab problems_
- _assign problems to students_; _assign grades_
- filter entities based on various criteria
- reports: e.g. find the problem that was assigned the most

### [Lab 7-10](https://github.com/VladCincean/labproblems-angular/tree/lab10%3Dlab9)
- convert your project (Lab 2-4) to a web application
- use _Angular 2_
- use _Spring_ (xml config forbidden)
- use _Spring Data JPA_ (_Hibernate_) (xml config forbidden)
- log messages using _SLF4J_
#### [Lab 7-8](https://github.com/VladCincean/labproblems-angular/tree/lab7-8): show the list of all _students_ and _lab problems_
#### [Lab 9-10](https://github.com/VladCincean/labproblems-angular/tree/lab10%3Dlab9): CRUD on both entities; filter; relation between the two entities

### [Lab 11](https://github.com/VladCincean/labproblems-angular/tree/lab11): Validation
- JSR 303/349 bean validation (working)
- Angular form validation (somehow working)

### [Lab 12](https://github.com/VladCincean/labproblems-angular/tree/lab12): handling the n + 1 select problem
- All associations _must_ be lazily loaded
    - change the fetch type from EAGER to LAZY in all relations => _lazy init exception_
    - do not use _@Transactional_ in the service methods that are responsible for reading the entities, e.g. _findAll_
    - fix the _lazy init exception_ using the 4 strategies below:
- Query the entities using:
    - Spring Queries with Named Entity Graphs
    - Native SQL: org.hibernate.Query
    - JPQL: javax.persistence.Query
    - JPA Criteria API

### [Lab 13](https://github.com/VladCincean/labproblems-angular/tree/lab13): Testing
- Write _integration tests_ for your repositories and services; use _DbUnit_, _xml datasets_
- Write _unit tests_ for your controllers using _Mockito_

### [Lab 14](https://github.com/VladCincean/labproblems-angular/tree/lab14): Security (not working)
- secure your Rest API using Spring security
    - password must be encrypted
    - restrict access to certain routes based on user roles
- secure the front-end
    - add a login/logout feature
    - restrict access to certain routes and page elements based on user roles