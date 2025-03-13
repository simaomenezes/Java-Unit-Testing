package github.com.simaomenezes.integrationtests.swagger;

import github.com.simaomenezes.config.TestConfig;
import github.com.simaomenezes.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	@DisplayName("JUnit test for Should Display Swagger UI page")
	void testShouldDisplaySwaggerUIPage() {
		String content = given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfig.SERVER_PORT)
				.when()
				.get()
				.then()
				.statusCode(200).extract().body().asString();

		assertTrue(content.contains("Swagger UI"));
	}
}
