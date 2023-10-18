package com.igr.walletservice.repository;

import com.igr.walletservice.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.igr.walletservice.jdbc.Liquibase.*;

/** Репозиторий пользователей */
public class UserRepository {
    String URL ;
    String USER_NAME ;
    String PASSWORD ;
    public UserRepository() throws IOException {
        Properties props = new Properties();
        try(
                InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String URL = props.getProperty("url");
        String USER_NAME = props.getProperty("username");
        String PASSWORD = props.getProperty("password");
    }
    List<User> userList = new ArrayList<>();
    public User findUserByLogin(String login) {
        User user = new User();
        try (Connection connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD)){
            String retrieveDataSQL ="SELECT * FROM users WHERE login ="+login;
            Statement retrieveDataStatement = connection.createStatement();
            ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void save(User user) {
        try (Connection connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD)){
            String insertUserSQL ="INSERT INTO users (login, password) VALUES (?, ?)";
            PreparedStatement insertDataStatement = connection.prepareStatement(insertUserSQL);
            insertDataStatement.setString(1, user.getLogin());
            insertDataStatement.setString(2, user.getPassword());
            insertDataStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(User user) {
        User user1 = findUserByLogin(user.getLogin());
        if (user1 != null) {
            try (Connection connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD)){
                String deleteDataSQL ="DELETE FROM users WHERE id ="+ Long.toString(user1.getId());
                Statement retrieveDataStatement = connection.createStatement();
                ResultSet resultSet = retrieveDataStatement.executeQuery(deleteDataSQL);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkPassword(User user) {
        User user1 = new User();
        try (Connection connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD)){
            String retrieveDataSQL ="SELECT * FROM users WHERE login ="+
                    user.getLogin()+" AND password ="+user.getPassword();
            Statement retrieveDataStatement = connection.createStatement();
            ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
            user.setId(resultSet.getInt("id"));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
