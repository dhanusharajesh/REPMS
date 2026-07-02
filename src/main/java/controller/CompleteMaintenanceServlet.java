package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/complete-maintenance")
public class CompleteMaintenanceServlet
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

            int requestId =
                    Integer.parseInt(
                            request.getParameter(
                                    "id"));

            boolean success =
                    service.completeMaintenanceRequest(
                            requestId);

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