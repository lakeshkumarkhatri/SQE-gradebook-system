package gradebook;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.Test;

class SchemaAndContractTest extends ApiTestBase {

    @Test
    void studentListMatchesJsonSchema() {
        addStudent("S1001", "Ayesha Khan");
        addStudent("S1002", "Bilal Ahmed");
        addScore("S1001", 90);

        given()
        .when().get("/students")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body(matchesJsonSchemaInClasspath("schemas/student-list.schema.json"));
    }

    @Test
    void getStudentsRespondsWithinPerformanceLimit() {
        addStudent("S1001", "Ayesha Khan");

        given()
        .when().get("/students")
        .then()
            .statusCode(200)
            .time(lessThan(1000L)); // Performance assertion (under 1000ms)
    }
}