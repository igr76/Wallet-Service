package com.igr.walletservice.jdbc;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
@Slf4j
public class Liquibase {
    public static void LiquibaseStart() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/jdbc",
                    "postgres",
                    "password"
            );
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            liquibase.Liquibase liquibase = new liquibase.Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            log.info("Миграции успешно выполнены!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
