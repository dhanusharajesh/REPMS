package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.BuyerProfileDTO;
import entity.LeaseRequest;
import entity.Tenant;
import entity.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/buyer-profile")
public class BuyerProfileServlet
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

        try{

            User user =
                    (User) session.getAttribute("user");

            Tenant tenant =
                    service.getTenantByUserId(
                            user.getUserId());

            int pendingRequests = 0;

            List<LeaseRequest> requests =
                    service.getAllLeaseRequests();

            for(LeaseRequest req : requests){

                if(req.getTenantId()
                        == tenant.getTenantId()){

                    pendingRequests++;
                }
            }

            BuyerProfileDTO dto =
                    new BuyerProfileDTO();

            dto.setTenantId(
                    tenant.getTenantId());

            dto.setFirstName(
                    tenant.getFirstName());

            dto.setLastName(
                    tenant.getLastName());

            dto.setEmail(
                    tenant.getEmail());

            dto.setPhone(
                    tenant.getPhone());

            dto.setUsername(
                    user.getUsername());

            dto.setRole(
                    user.getRole());

            dto.setPropertyId(
                    tenant.getPropertyId());

            dto.setTotalProperties(1);

            dto.setActiveLeases(1);

            dto.setPendingRequests(
                    pendingRequests);

            dto.setMaintenanceRequests(0);

            response.setContentType(
                    "application/json");

            response.getWriter().write(
                    new Gson().toJson(dto));

        }
        catch(Exception e){

            e.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
