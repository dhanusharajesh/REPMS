package entity;

import java.sql.Date;

public class Tenant {

    private int tenantId;
    private int propertyId;
    private int userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private java.sql.Date leaseStart;
    private java.sql.Date leaseEnd;

    public Tenant() {
    }

    public Tenant(int tenantId, int propertyId, String firstName, String lastName, String phone, String email, Date leaseStart, Date leaseEnd) {
        this.tenantId = tenantId;
        this.propertyId = propertyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(Date leaseStart) {
        this.leaseStart = leaseStart;
    }

    public Date getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(Date leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "tenantId=" + tenantId +
                ", propertyId=" + propertyId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", leaseStart=" + leaseStart +
                ", leaseEnd=" + leaseEnd +
                '}';
    }
}