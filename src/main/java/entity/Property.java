package entity;

public class Property {

    private int propertyId;
    private int ownerId;
    private String propertyName;
    private String address;
    private String propertyType;
    private double sizeSqft;
    private double rentAmount;
    private String status;
    private String imagePath;

    public Property() {
    }

    public Property(int propertyId, int ownerId, String propertyName, String address, String propertyType, double sizeSqft, double rentAmount, String status) {
        this.propertyId = propertyId;
        this.ownerId = ownerId;
        this.propertyName = propertyName;
        this.address = address;
        this.propertyType = propertyType;
        this.sizeSqft = sizeSqft;
        this.rentAmount = rentAmount;
        this.status = status;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public double getSizeSqft() {
        return sizeSqft;
    }

    public void setSizeSqft(double sizeSqft) {
        this.sizeSqft = sizeSqft;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", ownerId=" + ownerId +
                ", propertyName='" + propertyName + '\'' +
                ", address='" + address + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", sizeSqft=" + sizeSqft +
                ", rentAmount=" + rentAmount +
                ", status='" + status + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}