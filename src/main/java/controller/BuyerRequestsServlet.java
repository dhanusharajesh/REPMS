package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.LeaseRequest;
import entity.Tenant;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-requests")
public class BuyerRequestsServlet
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

        try {

            Tenant tenant =
                    service.getTenantByUserId(
                            userId);

            List<LeaseRequest> requests =
                    service.getLeaseRequestsByTenantId(
                            tenant.getTenantId());

            response.setContentType(
                    "application/json");

            response.setCharacterEncoding(
                    "UTF-8");

            Gson gson =
                    new Gson();

            response.getWriter().write(
                    gson.toJson(requests));

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(500);
        }
    }
}