package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.LeaseRequest;
import entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin-requests")
public class AdminRequestsServlet extends HttpServlet {

    private PropertyService service;

    @Override
    public void init() {
        service = new PropertyServiceImpl();
    }

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

        List<LeaseRequest> requests =
                service.getAllLeaseRequests();

        request.setAttribute(
                "requests",
                requests);

        request.getRequestDispatcher(
                        "admin-requests.jsp")
                .forward(request, response);
    }
}