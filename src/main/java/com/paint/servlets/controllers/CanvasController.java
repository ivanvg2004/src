package com.paint.servlets.controllers;

import com.paint.servlets.DAOS.CanvasDAO;
import com.paint.servlets.DAOS.UserDAO;
import com.paint.servlets.models.Canvas;
import com.paint.servlets.models.User;
import com.paint.servlets.services.CanvasService;
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
    private CanvasService canvasService = new CanvasService();
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
        String username = (String) session.getAttribute("user");
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
            canvasService.guardarDibuix(user, name, contingutJson);
            resp.sendRedirect(req.getContextPath() + "/canvas");
        }
    }
}
