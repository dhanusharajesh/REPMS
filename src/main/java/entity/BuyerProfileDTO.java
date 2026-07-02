package entity;

public class BuyerProfileDTO {

    private int tenantId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private String username;
    private String role;

    private int propertyId;

    private int totalProperties;
    private int activeLeases;
    private int pendingRequests;
    private int maintenanceRequests;

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getTotalProperties() {
        return totalProperties;
    }

    public void setTotalProperties(int totalProperties) {
        this.totalProperties = totalProperties;
    }

    public int getActiveLeases() {
        return activeLeases;
    }

    public void setActiveLeases(int activeLeases) {
        this.activeLeases = activeLeases;
    }

    public int getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(int pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public int getMaintenanceRequests() {
        return maintenanceRequests;
    }

    public void setMaintenanceRequests(int maintenanceRequests) {
        this.maintenanceRequests = maintenanceRequests;
    }
}