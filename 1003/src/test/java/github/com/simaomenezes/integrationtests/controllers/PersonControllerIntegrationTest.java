package github.com.simaomenezes.integrationtests.controllers;

import github.com.simaomenezes.config.TestConfigs;
import github.com.simaomenezes.integrationtests.testcontainers.AbstractIntegrationTest;
import github.com.simaomenezes.model.Person;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper mapper;
    private static Person person;

    @BeforeAll
    public static void setup(){
        // Given / Arrange
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person(
                "Fulano",
                "Silva",
                "fulano@mail.com",
                "Rua ZZ",
                "male"
            );
    }

    @Test
    @Order(1)
    @DisplayName("Junit integration Given Person Object when Create one Person should Return a Person Object ")
    void testGivenPersonObject_when_CreateOnePerson_ShouldReturnAPersonObject() throws IOException {
        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        Person createPerson = mapper.readValue(content, Person.class);
        person = createPerson;
        assertNotNull(createPerson);
    }
}
