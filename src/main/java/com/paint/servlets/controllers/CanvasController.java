package com.paint.servlets.controllers;

import com.paint.servlets.DAOS.CanvasDAO;
import com.paint.servlets.DAOS.UserDAO;
import com.paint.servlets.models.Canvas;
import com.paint.servlets.models.User;
import com.paint.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/canvas")
public class CanvasController extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null){
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/jsp/canvas.jsp")
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 1. Obtén el USERNAME (String) de la sesión
        String username = (String) session.getAttribute("user");
        // 2. Llama a tu nuevo metodo para obtener el objeto User
        User user = userService.getUserByUsername(username);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String name = req.getParameter("drawingName");
        String contingutJson = req.getParameter("drawingContent");

        if (name.isEmpty() && contingutJson.equals("[]")){
            resp.sendRedirect(req.getContextPath() + "/canvas");
        } else {
            // 4. Pasa el objeto User al DAO
            CanvasDAO.guardarDibuix(user, name, contingutJson);
            // 5. Redirige para evitar que se guarde de nuevo al recargar
            resp.sendRedirect(req.getContextPath() + "/canvas");
        }
    }
}
