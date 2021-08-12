package guru.qa.restbackend;

import guru.qa.restbackend.domain.ModelInfo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.with;

public class ModelsControllerTest {

    static {
        RestAssured.baseURI = "http://localhost:8081";
    }

    private RequestSpecification spec =
            with()
            .baseUri("http://localhost:8081")
            .basePath("/");

    @Test
    void bankControllerTest() {
        ModelInfo[] userInfos = spec.get("user/getAll")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(ModelInfo[].class);

        Stream.of(userInfos)
                .filter(userInfo -> userInfo.getUserName().equals("Dima"))
                .peek(userInfo -> System.out.println(userInfo.getUserName()))
                .map(userInfo -> userInfo.toString())
                .collect(Collectors.toList());
    }
}
