package com.calanco.todolist;

import com.calanco.todolist.adapters.DataAdapter;
import com.calanco.todolist.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new DataAdapter(req.getContextPath(),((User) req.getSession().getAttribute("User")).getId()).add(
                    req.getParameter("id"),
                    req.getParameter("title"),
                    req.getParameter("date"),
                    req.getParameter("type"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("index.jsp");

    }
}
