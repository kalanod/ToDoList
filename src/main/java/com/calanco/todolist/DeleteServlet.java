package com.calanco.todolist;

import com.calanco.todolist.adapters.DataAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/del")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataAdapter adapter = new DataAdapter(req.getContextPath());
        adapter.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("index.jsp");    }
}
