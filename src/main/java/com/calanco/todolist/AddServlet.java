package com.calanco.todolist;

import com.calanco.todolist.adapters.DataAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new DataAdapter(req.getContextPath()).add(
                req.getParameter("id"),
                req.getParameter("title"));
        resp.sendRedirect("index.jsp");

    }
}
