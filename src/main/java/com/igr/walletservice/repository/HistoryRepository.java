package com.igr.walletservice.repository;

import com.igr.walletservice.entity.History;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static com.igr.walletservice.jdbc.Liquibase.*;

/** Репозиторий истории */
public class HistoryRepository {
    String URL ;
    String USER_NAME ;
    String PASSWORD ;
    public HistoryRepository() throws IOException {
        Properties props = new Properties();
        try(
                InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String URL = props.getProperty("url");
        String USER_NAME = props.getProperty("username");
        String PASSWORD = props.getProperty("password");
    }
    List<History> historyList ;

    public void save(History history) {
        try (Connection connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD)){
            String insertUserSQL ="INSERT INTO history (localDateTime, login,beforeBalance,afterBalance)" +
                    " VALUES (?, ?,?,?)";
            PreparedStatement insertDataStatement = connection.prepareStatement(insertUserSQL);
            insertDataStatement.setString(1, String.valueOf(history.getLocalDateTime()));
            insertDataStatement.setString(2, history.getLogin());
            insertDataStatement.setDouble(3, history.getBeforeBalance());
            insertDataStatement.setDouble(3, history.getAfterBalance());
            insertDataStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getMyHistory(String login) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            ResultSet resultSet = retrieveHistory(connection,login);
            printHistory(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void printHistory(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String localDateTime = resultSet.getString("localDateTime");
            double beforeBalance = resultSet.getDouble("beforeBalance");
            double afterBalance = resultSet.getDouble("afterBalance");

            System.out.println("время: " + localDateTime + ", изначальный баланс: "
                    + beforeBalance + ", измененный баланс: " + afterBalance);
        }
    }

    private static ResultSet retrieveHistory(Connection connection,String login) throws SQLException {
        String retrieveDataSQL = "SELECT * FROM students WHERE login="+ login+"ORDER BY localDateTime";
        Statement retrieveDataStatement = connection.createStatement();
        ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
        return resultSet;
    }
}
