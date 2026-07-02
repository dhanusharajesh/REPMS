package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buyer-maintenance")
public class BuyerMaintenanceServlet
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
                session.getAttribute("user")
                        == null){

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        User user =
                (User) session.getAttribute("user");

        List<Property> properties =
                service.getPropertiesByUserId(
                        user.getUserId());

        List<MaintenanceRequest> requests =
                service.getAllMaintenanceRequests();

        List<MaintenanceView> result =
                new ArrayList<>();

        for(MaintenanceRequest req
                : requests){

            for(Property property
                    : properties){

                if(req.getPropertyId()
                        == property.getPropertyId()){

                    MaintenanceView view =
                            new MaintenanceView();

                    view.setRequestId(
                            req.getRequestId());

                    view.setPropertyName(
                            property.getPropertyName());

                    view.setIssueDescription(
                            req.getIssueDescription());

                    view.setRequestDate(
                            req.getRequestDate()
                                    .toString());

                    view.setStatus(
                            req.getStatus());

                    result.add(view);
                }
            }
        }

        response.setContentType(
                "application/json");

        Gson gson =
                new Gson();

        response.getWriter().write(
                gson.toJson(result));
    }
}