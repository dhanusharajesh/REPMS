package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.BookingRequest;
import entity.BookingRequestView;
import entity.Property;
import entity.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/seller-booking-requests")
public class BookingRequestsServlet
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

        List<BookingRequestView> result =
                new ArrayList<>();

        HttpSession session =
                request.getSession(false);

        if(session == null ||
                session.getAttribute("user")
                        == null){

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        User user =
                (User) session.getAttribute(
                        "user");

        int ownerId =
                service.getOwnerIdByUserId(
                        user.getUserId());

        List<Property> ownerProperties =
                service.getPropertiesByOwnerId(
                        ownerId);

        List<BookingRequest> requests =
                service.getAllBookingRequests();

        for(BookingRequest booking : requests){

            try{

                Property property =
                        service.getPropertyById(
                                booking.getPropertyId());

                boolean belongsToOwner =
                        false;

                for(Property p :
                        ownerProperties){

                    if(p.getPropertyId()
                            ==
                            property.getPropertyId()){

                        belongsToOwner =
                                true;

                        break;
                    }
                }

                if(!belongsToOwner){

                    continue;
                }

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