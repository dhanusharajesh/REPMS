package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import dao.PropertyService;
import dao.PropertyServiceImpl;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {

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

        entity.User user =
                (entity.User) session.getAttribute("user");

        if (!user.getRole()
                .equalsIgnoreCase("ADMIN")) {

            response.sendRedirect("my-properties");
            return;
        }
        PropertyService service =
                new PropertyServiceImpl();
        int totalProperties =
                service.getTotalProperties();

        int availableProperties =
                service.getAvailablePropertiesCount();

        int rentedProperties =
                service.getRentedPropertiesCount();

        int totalTenants =
                service.getTotalTenants();

        int pendingRequests =
                service.getPendingRequestsCount();

        request.setAttribute(
                "totalProperties",
                totalProperties);

        request.setAttribute(
                "availableProperties",
                availableProperties);

        request.setAttribute(
                "rentedProperties",
                rentedProperties);

        request.setAttribute(
                "totalTenants",
                totalTenants);

        request.setAttribute(
                "pendingRequests",
                pendingRequests);

        request.getRequestDispatcher(
                        "admin-dashboard.jsp")
                .forward(request, response);
    }
}