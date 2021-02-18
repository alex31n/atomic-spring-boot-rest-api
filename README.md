# Atomic REST API
A collection of boilerplate code and libraries for developing REST API with String Boot

The aim of creating this project to reduce boilerplate code. It can help you to faster way developing CURD-based Rest API and reduce more than 80% of code.

## How to use
Just create 4 class and extend their base class. Just it.

#### Model
```java
@Entity(name = "post")
public class Post  extends IdEntity {

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "content")
    private String content;

    // ----------------------
}
```

#### Repository
```java
@Repository
public interface PostRepository extends BaseRepository<Post> {
}
```

#### Service
```java
@Service
@Transactional
public class PostService extends BaseService<Post, PostRepository> {
    public PostService(PostRepository repository) {
        super(repository, "Post");  // "Post" is the model name
    }
}
```

#### Controller
```java
@RestController
@RequestMapping("post")
@Tag(name = "Posts")
public class PostController extends BaseController<Post, PostService> {
    public PostController(PostService service) {
        super(service);
    }
}
```

You are done. It will create CRUD API and documentation for you. For example

###### API ENDPOINT
```
[GET] /post (purpose for filtering/searching, pagination and sorting, detail below) 
[POST] /post
[GET] /post/{postId}
[PUT] /post/{postId}
[DELETE] /post/{postId}
```

###### API Documentation
```
{HOST}/docs
```

###### API Filter/Search
[GET] /user?search="name==\*Alex\*"&page=0&size=10&sort=name

Syntax Reference: [RSQL / FIQL parser](https://github.com/jirutka/rsql-parser#examples)

## Used technologies
- Spring boot 2.4.2
- java 8+
- Gradle
- Mysql
- Spring Data JPA
- spring-security
- devtools
- spring-validation
- [Lombok](https://projectlombok.org/)
- [mapstruct](https://github.com/mapstruct/mapstruct)
- [rsql-jpa-specification](https://github.com/perplexhub/rsql-jpa-specification)
- [jwt jsonwebtoken](https://github.com/jwtk/jjwt)
- [openapi-Swagger](https://springdoc.org/)



