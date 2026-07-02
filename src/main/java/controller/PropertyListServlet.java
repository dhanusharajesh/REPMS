package controller;
import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.Property;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import entity.Owner;
import entity.PropertyView;
import java.util.ArrayList;

@WebServlet("/properties")
public class PropertyListServlet extends HttpServlet {

    private PropertyService service;

    @Override
    public void init() {
        service = new PropertyServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        String ownerIdParam =
                request.getParameter("ownerId");

        List<Property> properties;
        List<PropertyView> result =
                new ArrayList<>();

        if(ownerIdParam != null &&
                !ownerIdParam.isEmpty()) {

            int ownerId =
                    Integer.parseInt(ownerIdParam);

            properties =
                    service.getPropertiesByOwnerId(
                            ownerId);

        } else {

            properties =
                    service.getAllProperties();
        }

        for(Property property : properties){

            try{

                Owner owner =
                        service.getOwnerById(
                                property.getOwnerId());

                PropertyView view =
                        new PropertyView();

                view.setPropertyId(
                        property.getPropertyId());

                view.setOwnerId(
                        property.getOwnerId());

                view.setPropertyName(
                        property.getPropertyName());

                view.setAddress(
                        property.getAddress());

                view.setPropertyType(
                        property.getPropertyType());

                view.setSizeSqft(
                        property.getSizeSqft());

                view.setRentAmount(
                        property.getRentAmount());

                view.setStatus(
                        property.getStatus());

                view.setImagePath(
                        property.getImagePath());

                view.setOwnerName(
                        owner.getFirstName()
                                + " "
                                + owner.getLastName());

                view.setOwnerPhone(
                        owner.getPhone());

                result.add(view);

            }catch(Exception e){

                e.printStackTrace();
            }
        }

        response.setContentType(
                "application/json");

        response.setCharacterEncoding(
                "UTF-8");

        Gson gson = new Gson();

        response.getWriter().write(
                gson.toJson(result));
    }
}