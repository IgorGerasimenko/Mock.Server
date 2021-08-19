package guru.qa.restbackend;

import guru.qa.restbackend.domain.ModelInfo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.*;

public class ModelsControllerTest {

    static {
        RestAssured.baseURI = "http://localhost:8081";
    }

    private RequestSpecification spec =
            with()
            .baseUri("http://localhost:8080")
            .basePath("/");

    @Test
    void getModelsListTest() {
        given()
                .spec(spec)
                .when()
                .get("/models/getAll")
                .then()
                .statusCode(200)
                .body("", hasItems(hasEntry("modelPath", "app/models/Комната 2.fbx")));
    }

    @Test
    void addModelTest() {
        given()
                .spec(spec)
                .contentType("application/json")
                .body("{\n" +
                        "\"modelPath\": \"app/models/Уфа модель.fbx\",\n" +
                        "\"modelName\": \"Уфа модель\",\n" +
                        "\"message\": null,\n" +
                        "\"ownerId\": 2,\n" +
                        "\"modelId\": null\n" +
                        "}")
                .when()
                .post("models/add")
                .then()
                .statusCode(200)
                .body("message", is("Модель успешно добавлена"));
    }

    @Test
    void renameModelTest() {
        String expResult = "Имя модели oldName успешно изменено на newName";
//        String expResultWithoutQuotes = expResult.substring(1, expResult.length() - 1);
        given()
                .spec(spec)
                .contentType("application/json")
                .when()
                .put("models/put/oldName/newName")
                .then()
                .statusCode(200)
                .body(contains(expResult));
    }

    @Test
    void deleteModelTest() {
        given()
                .spec(spec)
                .contentType("application/json")
                .when()
                .delete("models/put/Name")
                .then()
                .statusCode(200)
                .body(contains("Модель Name успешна удалена"));
    }

    @Test
    void findModelTest() {
        given()
                .spec(spec)
                .contentType("application/json")
                .when()
                .get("models/getByName/Уфа модель")
                .then()
                .statusCode(200)
                .body("modelName", is("Уфа модель"));
    }

}
