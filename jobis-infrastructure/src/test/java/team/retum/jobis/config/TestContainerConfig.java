package team.retum.jobis.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestContainerConfig {

    public static final MySQLContainer<?> MYSQL_CONTAINER;

    private static final String DATABASE_NAME = "jobis_test_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    static {
        MYSQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.35"))
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withReuse(true)
            .withCommand(
                "--character-set-server=utf8mb4",
                "--collation-server=utf8mb4_unicode_ci",
                "--default-time-zone=+09:00"
            );

        MYSQL_CONTAINER.start();
    }
}
