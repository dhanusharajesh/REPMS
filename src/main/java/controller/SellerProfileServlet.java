package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/seller-profile")
public class SellerProfileServlet
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

        try {

            HttpSession session =
                    request.getSession(false);

            if(session == null ||
                    session.getAttribute("user") == null){

                response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED);

                return;
            }

            User user =
                    (User) session.getAttribute("user");

            int ownerId =
                    service.getOwnerIdByUserId(
                            user.getUserId());

            Owner owner =
                    service.getOwnerById(
                            ownerId);

            List<Property> properties =
                    service.getPropertiesByOwnerId(
                            ownerId);

            int totalProperties =
                    properties.size();

            int availableProperties = 0;

            int rentedProperties = 0;

            for(Property property : properties){

                if("available".equalsIgnoreCase(
                        property.getStatus())){

                    availableProperties++;
                }

                if("rented".equalsIgnoreCase(
                        property.getStatus())){

                    rentedProperties++;
                }
            }

            int totalRequests = 0;

            List<LeaseRequest> requests =
                    service.getAllLeaseRequests();

            for(LeaseRequest req : requests){

                try{

                    Tenant tenant =
                            service.getTenantById(
                                    req.getTenantId());

                    Property property =
                            service.getPropertyById(
                                    tenant.getPropertyId());

                    if(property.getOwnerId()
                            == ownerId){

                        totalRequests++;
                    }

                }catch(Exception e){

                    e.printStackTrace();
                }
            }

            SellerProfileView profile =
                    new SellerProfileView();

            profile.setOwnerId(
                    owner.getOwnerId());

            profile.setFirstName(
                    owner.getFirstName());

            profile.setLastName(
                    owner.getLastName());

            profile.setEmail(
                    owner.getEmail());

            profile.setPhone(
                    owner.getPhone());

            profile.setAddress(
                    owner.getAddress());

            profile.setUsername(
                    user.getUsername());

            profile.setRole(
                    user.getRole());

            profile.setTotalProperties(
                    totalProperties);

            profile.setAvailableProperties(
                    availableProperties);

            profile.setRentedProperties(
                    rentedProperties);

            profile.setTotalRequests(
                    totalRequests);

            response.setContentType(
                    "application/json");

            response.setCharacterEncoding(
                    "UTF-8");

            Gson gson =
                    new Gson();

            response.getWriter().write(
                    gson.toJson(profile));

        } catch (Exception e) {

            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}