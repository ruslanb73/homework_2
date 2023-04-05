package crud.steps;

import config.ApiConfig;
import crud.models.AbilitiesResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Steps extends ApiConfig {
    /**
     * Конструктор для создания экземпляра класса
     *
     * @param requestSpecification спецификация RestAssured
     */
    public Steps(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public static String fdd(String name){
        return given(requestSpecification)
                .when()
                .get("/pokemon/" + name)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }

    public static List<AbilitiesResponse> abilitiesResponseList(String name){
        return List.of(given(requestSpecification)
                .when()
                .get("/pokemon/" + name)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getObject("abilities", AbilitiesResponse.class));


    }

}
