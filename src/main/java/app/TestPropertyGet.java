package app;

import dao.PropertyService;
import dao.PropertyServiceImpl;
import entity.Property;
import exception.PropertyNotFoundException;

public class TestPropertyGet {

    public static void main(String[] args) {

        try {

            PropertyService service =
                    new PropertyServiceImpl();

            Property property =
                    service.getPropertyById(1);

            System.out.println(property);

        } catch (PropertyNotFoundException e) {

            System.out.println(e.getMessage());

        }
    }
}