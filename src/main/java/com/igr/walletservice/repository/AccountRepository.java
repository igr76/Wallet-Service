package com.igr.walletservice.repository;


import java.sql.*;
import java.util.HashMap;

import static com.igr.walletservice.jdbc.Liquibase.*;

/** Репозиторий счетов*/
public class AccountRepository {

    public int increaseBalance(double money, String user) {
        double balance = getBalance(user)+money;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
            String updateTableSQL = "UPDATE account SET balance="+ balance+"" +
                    " WHERE login="+user;
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateTableSQL);
            connection.commit();
            System.out.println("Транзакция успешно завершена.");
            return 1;
        } catch (SQLException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Транзакция отменена.");
                } catch (SQLException rollbackException) {
                    System.err.println("Ошибка при откате транзакции: " + rollbackException.getMessage());
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeException) {
                System.err.println("Ошибка при закрытии соединения: " + closeException.getMessage());
            }
        }
        return 0;
    }

    public double reduceBalance(double money, String user) {
        double balance = getBalance(user);
        if (balance - money <= 0) {
            return 0;
        }
        balance = balance-money;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
            String updateTableSQL = "UPDATE account SET balance="+ balance+"" +
                    " WHERE login="+user;
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateTableSQL);
            connection.commit();
            System.out.println("Транзакция успешно завершена.");
            return balance;
        } catch (SQLException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Транзакция отменена.");
                } catch (SQLException rollbackException) {
                    System.err.println("Ошибка при откате транзакции: " + rollbackException.getMessage());
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeException) {
                System.err.println("Ошибка при закрытии соединения: " + closeException.getMessage());
            }
        }
       return -1;
    }

    public double getBalance(String user) {
        double balance = 0;
        try (Connection connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD)){
            String retrieveDataSQL ="SELECT balance FROM account WHERE login ="+user;
            Statement retrieveDataStatement = connection.createStatement();
            ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
            balance = resultSet.getInt("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
}
