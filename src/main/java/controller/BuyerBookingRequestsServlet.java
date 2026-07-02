package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.BookingRequest;
import entity.BookingRequestView;
import entity.Property;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buyer-booking-requests")
public class BuyerBookingRequestsServlet
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

        int userId =
                Integer.parseInt(
                        request.getParameter(
                                "userId"));

        List<BookingRequestView> result =
                new ArrayList<>();

        List<BookingRequest> requests =
                service.getBookingRequestsByUserId(
                        userId);

        for(BookingRequest booking : requests){

            try{

                Property property =
                        service.getPropertyById(
                                booking.getPropertyId());

                BookingRequestView view =
                        new BookingRequestView();

                view.setRequestId(
                        booking.getRequestId());

                view.setBuyerName(
                        booking.getFirstName()
                                + " "
                                +
                                booking.getLastName());

                view.setPropertyName(
                        property.getPropertyName());

                view.setLeaseStart(
                        booking.getLeaseStart()
                                .toString());

                view.setLeaseEnd(
                        booking.getLeaseEnd()
                                .toString());

                view.setStatus(
                        booking.getStatus());

                result.add(view);

            }catch(Exception e){

                e.printStackTrace();
            }
        }

        response.setContentType(
                "application/json");

        response.setCharacterEncoding(
                "UTF-8");

        Gson gson =
                new Gson();

        response.getWriter().write(
                gson.toJson(result));
    }
}