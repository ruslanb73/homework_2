package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.http.ContentType.JSON;

public class ApiConfig {
    protected static final String POCEMON = "/pokemon/";
    protected static BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());
    protected static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(JSON)
            .setBaseUri(config.baseUrl())
            .setAccept(JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();
}
