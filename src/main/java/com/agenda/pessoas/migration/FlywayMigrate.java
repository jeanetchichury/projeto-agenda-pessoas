package com.agenda.pessoas.migration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FlywayMigrate {
    private Properties properties=new Properties();
    private DataSource dataSource;

    public FlywayMigrate(){
        loadApplicationProperties();
    }

    private DataSource getDataSource() {
        String dataSourceUrl = properties.getProperty("spring.datasource.url");
        String driverClassName = properties.getProperty("spring.datasource.driver-class-name");
        String userName = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(dataSourceUrl);
        ds.setUsername(userName);
        ds.setPassword(password);

        dataSource=ds;

        return dataSource;
    }

    public void migrate() {
        String schema = properties.getProperty("spring.flyway.schemas");

        String classPath = properties.getProperty("spring.flyway.locations");

        FluentConfiguration flyway = Flyway
                .configure()
                .locations(classPath)
                .dataSource(getDataSource())
                .outOfOrder(true)
                .validateOnMigrate(false);

        flyway.schemas(schema);

        flyway.load().migrate();

        System.out.println("migration rodada com sucesso.");

    }

    private void loadApplicationProperties(){
        String propertiesFileName = "application.properties";

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try(InputStream resourceStream= loader.getResourceAsStream(propertiesFileName)){
            this.properties.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
