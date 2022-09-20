In the project, we will try to implement a REST API for a simple search/filtering of a list of users. We will use Spring Data JPA and Specifications to implement the search/filtering. We will also implement pagination and sorting using Pageable and Sort.

User Entity
```java
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Boolean active;

}
```

Demo REST API:

http://localhost:8080/user/specific-builder?name=&age=&active=&page=1&size=10&sortBy=&sortDirection=desc