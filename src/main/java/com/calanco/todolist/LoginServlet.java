package com.calanco.todolist;

import com.calanco.todolist.adapters.UserAdapter;
import com.calanco.todolist.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(0, req.getParameter("username"),
                req.getParameter("password"));
        UserAdapter adapter = new UserAdapter();
        if (!adapter.isAuthorized(user)) {
            adapter.add(user);
        }
        user = adapter.fillUser(user);
        req.getSession().setAttribute("User", user);
        resp.sendRedirect(req.getContextPath());
    }
}
