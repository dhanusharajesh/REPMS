package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.Property;
import entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import exception.PropertyNotFoundException;

@WebServlet("/edit-property")
public class EditPropertyServlet extends HttpServlet {

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

        int propertyId =
                Integer.parseInt(
                        request.getParameter("id"));

        PropertyService service =
                new PropertyServiceImpl();

        try {

            Property property =
                    service.getPropertyById(
                            propertyId);

            request.setAttribute(
                    "property",
                    property);

            request.getRequestDispatcher(
                            "edit-property.jsp")
                    .forward(request, response);

        } catch (PropertyNotFoundException e) {

            e.printStackTrace();

            response.sendRedirect(
                    "admin-properties");
        }
    }
}