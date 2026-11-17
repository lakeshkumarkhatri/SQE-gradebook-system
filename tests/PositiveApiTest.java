package gradebook;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

class PositiveApiTest extends ApiTestBase {

    @Test
    void registerStudentEndpointCreatesStudentSuccessfully() {
        given().contentType(JSON).body(Map.of("studentId", "S1001", "name", "Ayesha Khan"))
        .when().post("/students")
        .then()
            .statusCode(201)                          // Layer 1: Status code
            .contentType(JSON)                        // Layer 1: Content-Type header
            .body("studentId", equalTo("S1001"))      // Layer 3: Content check
            .body("name", equalTo("Ayesha Khan"))
            .body("average", equalTo(0));
    }

    @Test
    void getAllStudentsEndpointReturnsStudentArray() {
        addStudent("S1001", "Ayesha Khan");
        
        given()
        .when().get("/students")
        .then()
            .statusCode(200)                          // Layer 1: Status & Header
            .contentType(JSON)
            .body("$", hasSize(1))                    // Layer 2/3: Shape & Content
            .body("[0].studentId", equalTo("S1001"))
            .body("[0].name", equalTo("Ayesha Khan"));
    }

    @Test
    void getSingleStudentEndpointReturnsCorrectStudent() {
        addStudent("S1001", "Ayesha Khan");

        given()
        .when().get("/students/S1001")
        .then()
            .statusCode(200)                          // Layer 1
            .contentType(JSON)
            .body("studentId", equalTo("S1001"))      // Layer 3
            .body("name", equalTo("Ayesha Khan"));
    }

    @Test
    void recordScoreEndpointUpdatesScoreAndAverage() {
        addStudent("S1001", "Ayesha Khan");

        given().contentType(JSON).body(Map.of("score", 85))
        .when().post("/students/S1001/scores")
        .then()
            .statusCode(201)                          // Layer 1
            .contentType(JSON)
            .body("studentId", equalTo("S1001"))      // Layer 3
            .body("score", equalTo(85))
            .body("average", equalTo(85));
    }

    @Test
    void gradeEndpointReturnsLetterForAverage() {
        addStudent("S1001", "Ayesha Khan");
        addScore("S1001", 90);

        given()
        .when().get("/students/S1001/grade")
        .then()
            .statusCode(200)                          // Layer 1
            .contentType(JSON)
            .body("studentId", equalTo("S1001"))      // Layer 3
            .body("average", equalTo(90))
            .body("letter", equalTo("A"));
    }

    @Test
    void gradeScaleEndpointReturnsLetterForQueryParamScore() {
        given().queryParam("score", 75)
        .when().get("/grade-scale")
        .then()
            .statusCode(200)                          // Layer 1
            .contentType(JSON)
            .body("score", equalTo(75))               // Layer 3
            .body("letter", equalTo("C"));
    }
}