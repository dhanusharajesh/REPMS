package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.LeaseRequest;
import entity.Property;
import entity.SellerDashboardStats;
import entity.Tenant;
import entity.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/seller-dashboard")
public class SellerDashboardServlet
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
        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        User user =
                (User) session.getAttribute("user");

        int ownerId =
                service.getOwnerIdByUserId(
                        user.getUserId());

        List<Property> properties =
                service.getPropertiesByOwnerId(
                        ownerId);

        int totalProperties =
                properties.size();

        int available = 0;

        int rented = 0;

        for (Property property : properties) {

            if ("available".equalsIgnoreCase(
                    property.getStatus())) {

                available++;
            }

            if ("rented".equalsIgnoreCase(
                    property.getStatus())) {

                rented++;
            }
        }
        int requests = 0;

        List<LeaseRequest> allRequests =
                service.getAllLeaseRequests();

        for (LeaseRequest req : allRequests) {

            try {

                Tenant tenant =
                        service.getTenantById(
                                req.getTenantId());

                Property property =
                        service.getPropertyById(
                                tenant.getPropertyId());

                if (property.getOwnerId()
                        == ownerId) {

                    requests++;
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        SellerDashboardStats stats =
                new SellerDashboardStats();

        stats.setTotalProperties(
                totalProperties);

        stats.setAvailable(
                available);

        stats.setRented(
                rented);

        stats.setRequests(
                requests);

        response.setContentType(
                "application/json");

        Gson gson =
                new Gson();

        response.getWriter().write(
                gson.toJson(stats));
    }
}