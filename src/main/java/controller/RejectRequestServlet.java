package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/reject-request")
public class RejectRequestServlet
        extends HttpServlet {

    private PropertyService service;

    @Override
    public void init() {
        service = new PropertyServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int requestId =
                    Integer.parseInt(
                            request.getParameter("id"));

            service.rejectRequest(
                    requestId);

            response.sendRedirect(
                    "admin-requests");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    "admin-requests");
        }
    }
}