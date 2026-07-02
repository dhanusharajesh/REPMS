package entity;

public class MaintenanceView {

    private int requestId;

    private String propertyName;

    private String issueDescription;

    private String requestDate;

    private String status;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(
            String issueDescription) {

        this.issueDescription =
                issueDescription;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(
            String requestDate) {

        this.requestDate =
                requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }
}