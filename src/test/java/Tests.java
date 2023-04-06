import crud.models.AbilitiesResponse;
import crud.models.ResultsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static crud.steps.Steps.getAbilitiesResponseList;
import static crud.steps.Steps.getResultsResponseList;
import static crud.steps.Steps.getPokemonResponse;
import static io.restassured.RestAssured.given;

/**
 * Основной класс с тестами PokeApi
 */
@DisplayName("API тесты PokeApi")
public class Tests extends BaseTests {

    @DisplayName("Успешная проверка наличия умения run-away у покемона \"rattata\" ")
    @Test
    public void abilityRattataTest() {
        List<String> listAbilityRattata = getAbilitiesResponseList(POKEMON_RATTATA)
                .stream()
                .map(AbilitiesResponse::getAbility)
                .map(AbilitiesResponse.Ability::getName)
                .collect(Collectors.toList());
        for (int i = 0; i < listAbilityRattata.size(); i++) {
            Assertions.assertTrue(listAbilityRattata.contains("run-away"), "Умение run-away отсутствует");
        }
    }

    @DisplayName("Успешная проверка отсутствия умения run-away у покемона \"pidgeotto\" ")
    @Test
    public void abilityPidgeottoTest() {
        List<String> listAbilityPidgeotto = getAbilitiesResponseList(POKEMON_PIDGEOTTO)
                .stream()
                .map(AbilitiesResponse::getAbility)
                .map(AbilitiesResponse.Ability::getName)
                .collect(Collectors.toList());
        for (int i = 0; i < listAbilityPidgeotto.size(); i++) {
            Assertions.assertFalse(listAbilityPidgeotto.contains("run-away"), "Умение run-away имеется ");
        }
    }

    @DisplayName("Успешное сравнение веса покемонов Rattata и Pidgeotto")
    @Test
    public void weightComparisonSuccessTest() {
        int weightRattata = getPokemonResponse(POKEMON_RATTATA).getWeight();
        int weightPidgeotto = getPokemonResponse(POKEMON_PIDGEOTTO).getWeight();
        Assertions.assertTrue(weightRattata < weightPidgeotto, "Вес RATTATA больше PIDGEOTTO ");
    }

    @DisplayName("Имена в поле name соответствуют именам Rattata и Pidgeotto")
    @Test
    public void nameCorrespondSuccessTest() {
        String nameRattata = getPokemonResponse(POKEMON_RATTATA).getName();
        String namePidgeotto = getPokemonResponse(POKEMON_PIDGEOTTO).getName();
        Assertions.assertEquals(nameRattata, POKEMON_RATTATA, "Имя не соответствует");
        Assertions.assertEquals(namePidgeotto, POKEMON_PIDGEOTTO, "Имя не соответствует");
    }

    @DisplayName("Проверка ограничения списка покемонов")
    @Test
    public void checkingLimitPokemonTest() {
        List<ResultsResponse> resultsResponses20 = getResultsResponseList(20);
        List<ResultsResponse> resultsResponses10 = getResultsResponseList(10);

        Assertions.assertEquals(20, resultsResponses20.size());
        Assertions.assertEquals(10, resultsResponses10.size());
    }

    @DisplayName("Проверка в списке покемонов наличия имени")
    @Test
    public void presenceNamePokemonListTest() {
        List<ResultsResponse> resultsResponses = getResultsResponseList(20);
        System.out.println(resultsResponses);
        resultsResponses.forEach(x -> Assertions.assertTrue(x.getName().contains(x.getName())));
    }

    @DisplayName("Проверка ошибки ввода имени покемона ")
    @Test
    public void abilityRattataFailedTest() {
        String nameFailed = "raaa";
        String error = given(requestSpecification)
                .when()
                .get(POCEMON + nameFailed)
                .then()
                .assertThat()
                .statusCode(404)
                .extract()
                .body()
                .asString();
        Assertions.assertEquals(error, "Not Found", "Ошибка не соответствует");
    }
}
