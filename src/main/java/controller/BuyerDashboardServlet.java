package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.BuyerDashboardStats;
import entity.LeaseRequest;
import entity.Tenant;
import entity.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/buyer-dashboard")
public class BuyerDashboardServlet
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

        if(session == null ||
                session.getAttribute("user") == null){

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        User user =
                (User) session.getAttribute("user");

        Tenant tenant = null;

        try {

            tenant =
                    service.getTenantByUserId(
                            user.getUserId());

        } catch (Exception e) {

            e.printStackTrace();
        }

        int properties = 0;

        int pendingRequests = 0;

        int approvedRequests = 0;

        if(tenant != null){

            properties = 1;

            List<LeaseRequest> requests =
                    service.getAllLeaseRequests();

            for(LeaseRequest req : requests){

                if(req.getTenantId()
                        == tenant.getTenantId()){

                    if("PENDING".equalsIgnoreCase(
                            req.getStatus())){

                        pendingRequests++;
                    }

                    if("APPROVED".equalsIgnoreCase(
                            req.getStatus())){

                        approvedRequests++;
                    }
                }
            }
        }

        BuyerDashboardStats stats =
                new BuyerDashboardStats();

        stats.setProperties(
                properties);

        stats.setPendingRequests(
                pendingRequests);

        stats.setApprovedRequests(
                approvedRequests);

        response.setContentType(
                "application/json");

        Gson gson =
                new Gson();

        response.getWriter().write(
                gson.toJson(stats));
    }
}