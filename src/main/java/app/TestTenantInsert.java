package app;

import dao.PropertyServiceImpl;
import entity.Tenant;

import java.sql.Date;

public class TestTenantInsert {

    public static void main(String[] args) {

        Tenant tenant = new Tenant(
                0,
                1,
                "Rahul",
                "Sharma",
                "8887776665",
                "rahul@example.com",
                Date.valueOf("2025-01-01"),
                Date.valueOf("2025-12-31")
        );

        PropertyServiceImpl service =
                new PropertyServiceImpl();

        boolean result =
                service.addTenant(tenant);

        System.out.println(result);
    }
}