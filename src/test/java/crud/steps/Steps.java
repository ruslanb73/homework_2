package crud.steps;

import config.ApiConfig;
import crud.models.AbilitiesResponse;
import crud.models.ResultsResponse;
import crud.models.PokemonResponse;
import io.qameta.allure.Step;
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
        ApiConfig.requestSpecification = requestSpecification;
    }

    @Step("Получение Ability")
    public static List<AbilitiesResponse> getAbilitiesResponseList(String name) {
        return given(requestSpecification)
                .when()
                .get(POCEMON + name)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath().getList("abilities", AbilitiesResponse.class);
    }

    @Step("Получение Weight и Name у покемона")
    public static PokemonResponse getPokemonResponse(String name) {
        return given(requestSpecification)
                .when()
                .get(POCEMON + name)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(PokemonResponse.class);
    }

    @Step("Получение listResult")
    public static List<ResultsResponse> getResultsResponseList(int quantity ) {
        return given(requestSpecification)
                .when()
                .param("limit", quantity)
                .get(POCEMON)
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath().getList("results", ResultsResponse.class);
    }
}
