package com.igr.walletservice.repository;

import com.igr.walletservice.entity.History;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/** Репозиторий истории */
public class HistoryRepository {
    List<History> historyList ;
    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USER_NAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

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
