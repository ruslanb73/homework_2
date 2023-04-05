import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import crud.models.AbilitiesResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static crud.steps.Steps.abilitiesResponseList;




/**
 *
 */
@Epic("")
@Feature("")
public class Tests extends BaseTests  {

    @Test
    public void test() {

       List<AbilitiesResponse> ability = abilitiesResponseList("rattata");
    }


}
