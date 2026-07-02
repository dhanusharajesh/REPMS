package entity;

import java.sql.Date;

public class MaintenanceRequest {

    private int requestId;
    private int propertyId;
    private Integer tenantId;
    private String issueDescription;
    private java.sql.Date requestDate;
    private String status;

    public MaintenanceRequest() {
    }

    public MaintenanceRequest(int requestId, int propertyId, Integer tenantId, String issueDescription, Date requestDate, String status) {
        this.requestId = requestId;
        this.propertyId = propertyId;
        this.tenantId = tenantId;
        this.issueDescription = issueDescription;
        this.requestDate = requestDate;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MaintenanceRequest{" +
                "requestId=" + requestId +
                ", propertyId=" + propertyId +
                ", tenantId=" + tenantId +
                ", issueDescription='" + issueDescription + '\'' +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                '}';
    }
}