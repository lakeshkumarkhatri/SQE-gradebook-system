package gradebook;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import io.restassured.RestAssured;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ApiTestBase {
    @Value("${local.server.port}") int port;
    @Autowired GradebookService service;

    @BeforeEach
    void baseSetUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        service.reset();
    }

    public static void addStudent(String id, String name) {
        given().contentType(JSON).body(Map.of("studentId", id, "name", name))
        .when().post("/students")
        .then().statusCode(201);
    }

    public static void addScore(String id, int score) {
        given().contentType(JSON).body(Map.of("score", score))
        .when().post("/students/" + id + "/scores")
        .then().statusCode(201);
    }
}