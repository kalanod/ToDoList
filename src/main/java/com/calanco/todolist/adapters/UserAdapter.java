package com.calanco.todolist.adapters;

import com.calanco.todolist.model.User;

import java.sql.SQLException;

public class UserAdapter {
    DbHandler dbHandler;
    public UserAdapter(){
        try {
            dbHandler = new DbHandler();
            dbHandler.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuthorized(User user) {
        return dbHandler.getUser(user) != null;
    }

    public User fillUser(User user) {
        return dbHandler.getUser(user);
    }

    public void add(User user) {
        dbHandler.addUser(user);
    }
}
