package com.calanco.todolist;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.*;
@WebServlet("/home")
public class HelloServlet extends HttpServlet {
    private Names names;
    public void init() {
        names = new Names();
        names.loadFromFile("db.txt");

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}