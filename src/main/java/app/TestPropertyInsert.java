package app;

import dao.PropertyServiceImpl;
import entity.Property;

public class TestPropertyInsert {

    public static void main(String[] args) {

        Property property = new Property(
                0,
                3,
                "Sunrise Villa",
                "Bangalore",
                "Villa",
                2500,
                45000,
                "available"
        );

        PropertyServiceImpl service =
                new PropertyServiceImpl();

        boolean result =
                service.addProperty(property);

        System.out.println(result);
    }
}