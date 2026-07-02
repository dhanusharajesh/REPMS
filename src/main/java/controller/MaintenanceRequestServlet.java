package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.MaintenanceRequest;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/maintenance-request")
public class MaintenanceRequestServlet
        extends HttpServlet {

    private PropertyService service;

    @Override
    public void init() {
        service = new PropertyServiceImpl();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {


        try {

            int propertyId =
                    Integer.parseInt(
                            request.getParameter(
                                    "propertyId"));

            String description =
                    request.getParameter(
                            "description");

            MaintenanceRequest maintenance =
                    new MaintenanceRequest();

            maintenance.setPropertyId(
                    propertyId);

            maintenance.setTenantId(
                    null);

            maintenance.setIssueDescription(
                    description);

            maintenance.setRequestDate(
                    new Date(
                            System.currentTimeMillis()));

            maintenance.setStatus(
                    "ACTIVE");

            boolean success =
                    service.logRequest(
                            maintenance);

            response.setContentType(
                    "application/json");

            response.getWriter().write(
                    "{\"success\":"
                            + success +
                            "}");

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(500);
        }
    }
}