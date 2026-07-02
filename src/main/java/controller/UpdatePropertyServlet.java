package controller;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.Property;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/update-property")
public class UpdatePropertyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Property property =
                new Property();

        property.setPropertyId(
                Integer.parseInt(
                        request.getParameter("propertyId")));

        property.setOwnerId(
                Integer.parseInt(
                        request.getParameter("ownerId")));

        property.setPropertyName(
                request.getParameter("propertyName"));

        property.setAddress(
                request.getParameter("address"));

        property.setPropertyType(
                request.getParameter("propertyType"));

        property.setSizeSqft(
                Double.parseDouble(
                        request.getParameter("sizeSqft")));

        property.setRentAmount(
                Double.parseDouble(
                        request.getParameter("rentAmount")));

        property.setStatus(
                request.getParameter("status"));

        property.setImagePath(
                request.getParameter("imagePath"));

        PropertyService service =
                new PropertyServiceImpl();

        boolean updated =
                service.updateProperty(property);

        if(updated){

            response.sendRedirect(
                    "admin-properties?success=property-updated");

        }else{

            response.sendRedirect(
                    "admin-properties?error=property-update-failed");
        }
    }
}