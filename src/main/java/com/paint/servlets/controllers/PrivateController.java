package com.paint.servlets.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/private")
public class PrivateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if (user == null){
            // Usuario no ha iniciado sesión, redirigir a login
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // El usuario ha iniciado sesión, simplemente mostramos la página
        // No es necesario req.setAttribute("user", user), el JSP puede leerlo de la sesión.
        req.getRequestDispatcher("/WEB-INF/jsp/private.jsp")
                .forward(req, resp);
    }
}
