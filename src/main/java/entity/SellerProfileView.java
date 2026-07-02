package entity;

public class SellerProfileView {

    private int ownerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String username;

    private String role;

    private int totalProperties;

    private int availableProperties;

    private int rentedProperties;

    private int totalRequests;

    public SellerProfileView() {
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getTotalProperties() {
        return totalProperties;
    }

    public void setTotalProperties(int totalProperties) {
        this.totalProperties = totalProperties;
    }

    public int getAvailableProperties() {
        return availableProperties;
    }

    public void setAvailableProperties(int availableProperties) {
        this.availableProperties = availableProperties;
    }

    public int getRentedProperties() {
        return rentedProperties;
    }

    public void setRentedProperties(int rentedProperties) {
        this.rentedProperties = rentedProperties;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    @Override
    public String toString() {
        return "SellerProfileView{" +
                "ownerId=" + ownerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", totalProperties=" + totalProperties +
                ", availableProperties=" + availableProperties +
                ", rentedProperties=" + rentedProperties +
                ", totalRequests=" + totalRequests +
                '}';
    }
}