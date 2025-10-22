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
        // El método GET solo debe mostrar la vista
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // El método POST gestiona el envío del formulario
        String name = req.getParameter("name");
        String user = req.getParameter("user");
        String password = req.getParameter("password");

        // 1. Comprobar si el nombre de usuario ya existe
        if (UserDAO.doesUsernameExist(user)) {
            // Si existe, devolvemos al formulario con un error
            req.setAttribute("message", "Aquest nom d'usuari ja existeix");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
        } else {
            // 2. Si no existe, creamos el nuevo usuario
            User newUser = new User(name, user, password);
            // 3. Lo añadimos a la "base de datos"
            UserDAO.addUser(newUser);

            // 4. Enviamos al usuario a la página de login con un mensaje de éxito
            req.setAttribute("message", "Usuari registrat correctament! Si us plau, inicia sessió.");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
