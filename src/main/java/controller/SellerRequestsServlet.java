package controller;

import com.google.gson.Gson;

import dao.PropertyService;
import dao.PropertyServiceImpl;

import entity.LeaseRequest;
import entity.Property;
import entity.RequestView;
import entity.Tenant;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import jakarta.servlet.http.HttpSession;

@WebServlet("/seller-requests")
public class SellerRequestsServlet
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
        List<RequestView> result =
                new ArrayList<>();

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

        int ownerId =
                service.getOwnerIdByUserId(
                        user.getUserId());

        System.out.println(
                "LOGGED USER = "
                        + user.getUserId());

        System.out.println(
                "OWNER ID = "
                        + ownerId);

        List<Property> ownerProperties =
                service.getPropertiesByOwnerId(
                        ownerId);

        System.out.println(
                "OWNER PROPERTIES = "
                        + ownerProperties.size());

        List<LeaseRequest> allRequests =
                service.getAllLeaseRequests();

        for(LeaseRequest req : allRequests){
            System.out.println(
                    "REQUEST = " +
                            req.getRequestId() +
                            " TENANT = " +
                            req.getTenantId()
            );

            try{
                System.out.println(
                        "FETCHING TENANT..."
                );

                Tenant tenant =
                        service.getTenantById(
                                req.getTenantId());

                if(req.getPropertyId() <= 0){
                    continue;
                }

                Property property =
                        service.getPropertyById(
                                req.getPropertyId());

                boolean belongsToOwner = false;

                for(Property p : ownerProperties){

                    if(p.getPropertyId()
                            == property.getPropertyId()){

                        belongsToOwner = true;
                        break;
                    }
                }

                if(!belongsToOwner){

                    continue;
                }

                RequestView view =
                        new RequestView();

                view.setRequestId(
                        req.getRequestId());

                view.setTenantName(
                        tenant.getFirstName()
                                + " "
                                + tenant.getLastName());

                view.setPropertyName(
                        property.getPropertyName());

                view.setRequestType(
                        req.getRequestType());

                view.setReason(
                        req.getReason());

                view.setStatus(
                        req.getStatus());

                view.setNewPhone(
                        req.getNewPhone());

                view.setNewEmail(
                        req.getNewEmail());

                view.setCreatedAt(
                        req.getCreatedAt().toString());

                if(req.getNewLeaseEnd()!=null){

                    view.setNewLeaseEnd(
                            req.getNewLeaseEnd().toString());
                }

                result.add(view);

            }catch(Exception e){

                System.out.println(
                        "FAILED REQUEST = "
                                + req.getRequestId());

                e.printStackTrace();
            }
        }

        response.setContentType(
                "application/json");

        response.setCharacterEncoding(
                "UTF-8");

        Gson gson =
                new Gson();

        response.getWriter().write(
                gson.toJson(result));
    }
}