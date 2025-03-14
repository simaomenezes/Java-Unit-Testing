package github.com.simaomenezes.integrationtests.testcontainers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.Module;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static jakarta.transaction.Transactional.TxType.NOT_SUPPORTED;
import static jakarta.transaction.Transactional.TxType.REQUIRED;

@Testcontainers
public class AbstractIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:15");

    @BeforeAll
    static void beforeAll() {
        POSTGRES_CONTAINER.start();
        System.setProperty("spring.datasource.url", POSTGRES_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRES_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRES_CONTAINER.getPassword());
    }

    @AfterEach
    @Transactional(REQUIRED)
    void cleanDatabase() {
        String sql = "TRUNCATE TABLE person RESTART IDENTITY CASCADE;";
        jdbcTemplate.execute(sql);
    }

    @AfterAll
    static void afterAll() {
        POSTGRES_CONTAINER.stop();
    }
}
