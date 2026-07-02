package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.LeaseRequest;
import entity.Tenant;
import entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/lease-request")
public class LeaseRequestServlet extends HttpServlet {

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

        String type =
                request.getParameter("type");

        request.setAttribute(
                "requestType",
                type);

        request.getRequestDispatcher(
                        "lease-request.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        try {

            User user =
                    (User) session.getAttribute("user");

            int propertyId =
                    Integer.parseInt(
                            request.getParameter(
                                    "propertyId"));

            Tenant tenant =
                    service.getTenantByUserId(
                            user.getUserId());

            String requestType =
                    request.getParameter("requestType");

            String reason =
                    request.getParameter("reason");

            LeaseRequest leaseRequest =
                    new LeaseRequest();

            leaseRequest.setTenantId(
                    tenant.getTenantId());

            leaseRequest.setPropertyId(
                    propertyId);

            leaseRequest.setRequestType(
                    requestType);

            leaseRequest.setReason(
                    reason);

            if ("UPDATE".equalsIgnoreCase(requestType)) {

                leaseRequest.setNewPhone(
                        request.getParameter("newPhone"));

                leaseRequest.setNewEmail(
                        request.getParameter("newEmail"));

                String leaseEnd =
                        request.getParameter(
                                "newLeaseEnd");

                if (leaseEnd != null &&
                        !leaseEnd.isEmpty()) {

                    leaseRequest.setNewLeaseEnd(
                            java.sql.Date.valueOf(
                                    leaseEnd));
                }
            }

            boolean success =
                    service.createLeaseRequest(
                            leaseRequest);

            if (success) {

                response.sendRedirect(
                        "my-properties?success=request-submitted");

            } else {

                response.sendRedirect(
                        "lease-request?type="
                                + requestType);
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    "my-properties");
        }
    }
}