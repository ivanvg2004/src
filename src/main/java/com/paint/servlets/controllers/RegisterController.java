package com.paint.servlets.controllers;

import com.paint.servlets.models.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
                .forward(req, resp);
        String name = req.getParameter("name");
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        UserDAO acount = new UserDAO();
        boolean userExist = acount.checkUser(user, password);
        if (userExist){
            req.setAttribute("message", "Username / password ja existeix");
        }else {
            acount = new UserDAO(name, user, password);
        }
    }
}
