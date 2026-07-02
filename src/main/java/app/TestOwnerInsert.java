package app;

import dao.PropertyServiceImpl;
import entity.Owner;

public class TestOwnerInsert {

    public static void main(String[] args) {

        Owner owner = new Owner(
                0,
                "Akshaya",
                "Ashok",
                "87596043218",
                "akshaya@example.com",
                "Chennai"
        );

        PropertyServiceImpl service =
                new PropertyServiceImpl();

        boolean result =
                service.addOwner(owner);

        System.out.println(result);
    }
}