package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.Property;
import entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-properties")
public class AdminPropertiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        User user =
                (User) session.getAttribute("user");

        if (!user.getRole()
                .equalsIgnoreCase("ADMIN")) {

            response.sendRedirect("my-properties");
            return;
        }

        PropertyService service =
                new PropertyServiceImpl();

        List<Property> properties =
                service.getAllProperties();

        request.setAttribute(
                "properties",
                properties);

        request.getRequestDispatcher(
                        "admin-properties.jsp")
                .forward(request, response);
    }
}