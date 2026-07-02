package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/delete-property")
public class DeletePropertyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int propertyId =
                Integer.parseInt(
                        request.getParameter("id"));

        PropertyService service =
                new PropertyServiceImpl();

        boolean deleted =
                service.deleteProperty(
                        propertyId);

        if(deleted){

            response.sendRedirect(
                    "admin-properties?success=property-deleted");

        }else{

            response.sendRedirect(
                    "admin-properties?error=property-delete-failed");
        }
    }
}