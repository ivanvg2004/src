package com.paint.servlets.controllers;

import com.paint.servlets.models.User;
import com.paint.servlets.DAOS.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String user = req.getParameter("user");
        String password = req.getParameter("password");

        if (UserDAO.doesUsernameExist(user)) {
            req.setAttribute("message", "Aquest nom d'usuari ja existeix");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
        } else {
            User newUser = new User(name, user, password);
            UserDAO.addUser(newUser);

            req.setAttribute("message", "Usuari registrat correctament! Si us plau, inicia sessió.");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                    .forward(req, resp);
        }
    }
}
