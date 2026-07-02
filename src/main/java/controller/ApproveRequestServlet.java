package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.LeaseRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/approve-request")
public class ApproveRequestServlet
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

        try {

            int requestId =
                    Integer.parseInt(
                            request.getParameter("id"));

            LeaseRequest leaseRequest =
                    service.getLeaseRequestById(
                            requestId);

            boolean success = false;

            if ("UPDATE".equalsIgnoreCase(
                    leaseRequest.getRequestType())) {

                success =
                        service.approveUpdateRequest(
                                requestId);

            } else if ("TERMINATION".equalsIgnoreCase(
                    leaseRequest.getRequestType())) {

                success =
                        service.approveTerminationRequest(
                                requestId);
            }

            response.sendRedirect(
                    "admin-requests");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    "admin-requests");
        }
    }
}