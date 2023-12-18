package com.calanco.todolist.adapters;

import com.calanco.todolist.model.ListItem;
import com.calanco.todolist.model.User;
import org.sqlite.JDBC;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

public class DbHandler {

    private static final String CON_STR = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\listDb.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    public DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }

//    public ArrayList<ListItem> getAlllists(String title, String author, String key, String date) {
//
//        try (Statement statement = this.connection.createStatement()) {
//            ArrayList<Article> products = new ArrayList<Article>();
//            StringBuilder query = new StringBuilder("SELECT * FROM lists");
//            int i = 0;
//            if (!title.equals("all") && !title.equals("")) {
//                query.append(" where ");
//                query.append("title='").append(title).append("'");
//                i = 1;
//            }
//            if (!author.equals("all") && !author.equals("")) {
//                if (i == 1) {
//                    query.append(" and author='").append(author).append("'");
//                } else {
//                    query.append(" where ");
//                    query.append("author='" + author + "'");
//                    i = 1;
//                }
//            }
//            if (!key.equals("all") && !key.equals("")) {
//                if (i == 1) {
//                    query.append(" and key_ LIKE '%" + key + "%'");
//                } else {
//                    query.append(" where ");
//                    query.append("key_ LIKE '% %" + key + "% %'");
//                    i = 1;
//                }
//            }
//            if (!date.equals("all") && !date.equals("")) {
//                if (i == 1) {
//                    query.append(" and date_='" + date + "'");
//                } else {
//                    query.append(" where ");
//                    query.append("date_='" + date + "'");
//                    i = 1;
//                }
//            }
//            System.out.println(query);
//            query.append(";");
//            ResultSet resultSet = statement.executeQuery(query.toString());
//            while (resultSet.next()) {
//                products.add(new Article(
//                        resultSet.getInt("id"),
//                        resultSet.getString("title"),
//                        resultSet.getString("author"),
//                        resultSet.getString("key_"),
//                        resultSet.getString("date_"),
//                        resultSet.getString("owner")));
//            }
//            // Возвращаем наш список
//            return products;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Если произошла ошибка - возвращаем пустую коллекцию
//            return new ArrayList<>();
//        }
//    }

    public String getListByUserId(String id) {
        try (Statement statement = this.connection.createStatement()) {
            String item = null;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM lists where user_id ='" + id + "';");
            while (resultSet.next()) {
                item = resultSet.getString("list");
            }
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return null;
        }
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS lists " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " user_id TEXT NOT NULL, " +
                " list TEXT NOT NULL);";
        PreparedStatement statemen = connection.prepareStatement(sql);
        statemen.execute();
        sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " username TEXT NOT NULL, " +
                " password TEXT NOT NULL);";
        statemen = connection.prepareStatement(sql);
        statemen.execute();
    }

    public void addList(String id, String list) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO lists(`user_id`, `list`) " +
                        "VALUES(?, ?)")) {

            statement.setObject(1, id);
            statement.setObject(2, list);
            statement.execute();
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление списков
    public void deleteAll() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM lists;")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(User user) {
        try (Statement statement = this.connection.createStatement()) {
            User item = null;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users where username = '" + user.getUsername() + "'" +
                    " and password = '"+user.getPassword()+"';");
            while (resultSet.next()) {
                item = new User(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"));
            }
            return item;
        } catch (Exception e) {
            // Если произошла ошибка - возвращаем пустую коллекцию
            return null;
        }
    }

    public void addUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users(`username`, `password`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, user.getUsername());
            statement.setObject(2, user.getPassword());
            statement.execute();
            // Выполняем запрос
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}