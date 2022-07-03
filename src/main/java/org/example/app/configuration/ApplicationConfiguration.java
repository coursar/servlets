package org.example.app.configuration;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.app.controller.UserController;
import org.example.framework.handler.WebHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public Map<String, WebHandler> handlers(final UserController userController) {
        return Map.of(
                "/users.getAll", userController::getAll, // method reference
                "/users.getById", userController::getById,
                "/users.create", userController::create
        );
    }

    @Bean(destroyMethod = "close")
    // initMethod = какой метод вызывать при инициализации
    // destroyMethod = какой метод вызывать при закрытии контекста
    public HikariDataSource dataSource() {
        final HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/db?user=app&password=password");
        configuration.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(configuration);
    }

    @Bean
    // тип возвращаемого значения: общее соглашение - интерфейс, а не конкретную реализацию
    public NamedParameterJdbcOperations namedParameterJdbcOperations(final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource); // DataSource???
    }
}
