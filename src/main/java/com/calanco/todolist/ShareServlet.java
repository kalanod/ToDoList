package com.calanco.todolist;

import com.calanco.todolist.adapters.DataAdapter;
import com.calanco.todolist.model.ListItem;
import com.calanco.todolist.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ListItem elem = new DataAdapter(req.getContextPath(), ((User) req.getSession().getAttribute("User")).getId())
                    .getLists().getArrayList().get(Integer.parseInt(req.getParameter("idElem")));
            new DataAdapter(req.getContextPath(), Integer.parseInt(req.getParameter("idUser2")))
                    .addToRoot(elem);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("index.jsp");

    }
}
