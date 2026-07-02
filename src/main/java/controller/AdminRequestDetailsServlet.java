package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.LeaseRequest;
import entity.Property;
import entity.Tenant;
import entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/request-details")
public class AdminRequestDetailsServlet
        extends HttpServlet {

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

        if(session == null ||
                session.getAttribute("user") == null){

            response.sendRedirect("login.jsp");
            return;
        }

        User loggedUser =
                (User) session.getAttribute("user");

        if(!loggedUser.getRole()
                .equalsIgnoreCase("ADMIN")){

            response.sendRedirect("my-properties");
            return;
        }

        try {

            int requestId =
                    Integer.parseInt(
                            request.getParameter("id"));

            LeaseRequest leaseRequest =
                    service.getLeaseRequestById(
                            requestId);

            Tenant tenant =
                    service.getTenantById(
                            leaseRequest.getTenantId());

            Property property =
                    service.getPropertyById(
                            tenant.getPropertyId());

            User tenantUser =
                    service.getUserById(
                            tenant.getUserId());

            request.setAttribute(
                    "leaseRequest",
                    leaseRequest);

            request.setAttribute(
                    "tenant",
                    tenant);

            request.setAttribute(
                    "property",
                    property);

            request.setAttribute(
                    "tenantUser",
                    tenantUser);

            request.getRequestDispatcher(
                            "request-details.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}