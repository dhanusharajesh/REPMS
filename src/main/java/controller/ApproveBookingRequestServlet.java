package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/approve-booking-request")
public class ApproveBookingRequestServlet
        extends HttpServlet {

    private PropertyService service;

    @Override
    public void init() {

        service =
                new PropertyServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int requestId =
                Integer.parseInt(
                        request.getParameter(
                                "id"));

        boolean success =
                service.approveBookingRequest(
                        requestId);

        response.getWriter().write(
                String.valueOf(success));
    }
}