package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private PropertyService service;

    @Override
    public void init() {
        service = new PropertyServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        String propertyId =
                request.getParameter("propertyId");

        User user =
                service.validateUser(
                        username,
                        password);

        if (user != null) {
            int ownerId =
                    service.getOwnerIdByUserId(
                            user.getUserId());

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "user",
                    user);

            response.setContentType(
                    "application/json");

            response.setCharacterEncoding(
                    "UTF-8");

            String json =
                    "{"
                            + "\"success\":true,"
                            + "\"userId\":" + user.getUserId() + ","
                            + "\"ownerId\":" + ownerId + ","
                            + "\"username\":\"" + user.getUsername() + "\","
                            + "\"role\":\"" + user.getRole() + "\""
                            + "}";

            response.getWriter().write(json);

        } else {

            response.setContentType(
                    "application/json");

            response.getWriter().write(
                    "{\"success\":false}"
            );
        }
    }
}