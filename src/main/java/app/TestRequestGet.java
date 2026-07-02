package app;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.MaintenanceRequest;

public class TestRequestGet {

    public static void main(String[] args) {

        PropertyService service =
                new PropertyServiceImpl();

        MaintenanceRequest request =
                service.getRequestById(1);

        System.out.println(request);
    }
}