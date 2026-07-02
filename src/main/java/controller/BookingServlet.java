package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.Property;
import entity.Tenant;
import entity.User;
import entity.BookingRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

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

        int propertyId =
                Integer.parseInt(
                        request.getParameter("propertyId"));

        try {

            Property property =
                    service.getPropertyById(propertyId);

            request.setAttribute(
                    "property",
                    property);

            request.getRequestDispatcher(
                            "booking.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        int propertyId =
                Integer.parseInt(
                        request.getParameter("propertyId"));

        String firstName =
                request.getParameter("firstName");

        String lastName =
                request.getParameter("lastName");

        String phone =
                request.getParameter("phone");

        String email =
                request.getParameter("email");

        java.sql.Date leaseStart =
                java.sql.Date.valueOf(
                        request.getParameter("leaseStart"));

        java.sql.Date leaseEnd =
                java.sql.Date.valueOf(
                        request.getParameter("leaseEnd"));

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");

        BookingRequest bookingRequest =
                new BookingRequest();

        bookingRequest.setPropertyId(
                propertyId);

        bookingRequest.setUserId(
                user.getUserId());

        bookingRequest.setFirstName(
                firstName);

        bookingRequest.setLastName(
                lastName);

        bookingRequest.setPhone(
                phone);

        bookingRequest.setEmail(
                email);

        bookingRequest.setLeaseStart(
                leaseStart);

        bookingRequest.setLeaseEnd(
                leaseEnd);

        boolean success =
                service.createBookingRequest(
                        bookingRequest);

        System.out.println("SUCCESS = " + success);

        if(success){

            response.sendRedirect("success.jsp");

        }
        else{

            response.sendRedirect("booking?propertyId="
                    + propertyId);

        }
    }
}