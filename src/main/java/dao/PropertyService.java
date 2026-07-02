package dao;

import entity.*;
import exception.OwnerNotFoundException;
import exception.PropertyNotFoundException;
import exception.TenantNotFoundException;
import exception.PaymentProcessingException;
import exception.MaintenanceRequestException;
import java.util.List;
import entity.User;
import entity.LeaseRequest;

public interface PropertyService {

    // Property
    boolean addProperty(Property property);
    boolean updateProperty(Property property);
    boolean deleteProperty(int propertyId);
    Property getPropertyById(int propertyId)
            throws PropertyNotFoundException;
    List<Property> getAllProperties();
    List<Property> getPropertiesByUserId(int userId);
    List<Property> getPropertiesByOwnerId(int ownerId);

    // Owner
    boolean addOwner(Owner owner);
    boolean updateOwner(Owner owner);
    boolean deleteOwner(int ownerId);
    Owner getOwnerById(int ownerId)
            throws OwnerNotFoundException;

    // Tenant
    boolean addTenant(Tenant tenant);
    boolean updateTenant(Tenant tenant);
    boolean deleteTenant(int tenantId);
    Tenant getTenantById(int tenantId)
            throws TenantNotFoundException;
    Tenant getTenantByUserId(int userId)
            throws TenantNotFoundException;

    // Lease
    boolean assignLease(Tenant tenant);
    boolean updateLease(Tenant tenant);
    boolean terminateLease(int tenantId);

    // Lease Requests
    boolean createLeaseRequest(LeaseRequest request);
    LeaseRequest getLeaseRequestById(int requestId);
    List<LeaseRequest> getAllLeaseRequests();
    List<LeaseRequest> getLeaseRequestsByTenantId(int tenantId);

    // Payment
    boolean processPayment(Payment payment)
            throws PaymentProcessingException;
    boolean updatePayment(Payment payment);
    boolean deletePayment(int paymentId);
    Payment getPaymentById(int paymentId);

    // Maintenance
    boolean logRequest(MaintenanceRequest request)
            throws MaintenanceRequestException;
    boolean updateRequest(MaintenanceRequest request);
    boolean deleteRequest(int requestId);
    MaintenanceRequest getRequestById(int requestId);
    List<MaintenanceRequest> getAllMaintenanceRequests();
    boolean completeMaintenanceRequest(
            int requestId);

    User validateUser(String username, String password);
    User getUserById(int userId);
    int getOwnerIdByUserId(int userId);

    boolean approveUpdateRequest(int requestId);
    boolean approveTerminationRequest(int requestId);
    boolean rejectRequest(int requestId);

    int getTotalProperties();
    int getAvailablePropertiesCount();
    int getRentedPropertiesCount();
    int getTotalTenants();
    int getPendingRequestsCount();

    boolean createBookingRequest(
            BookingRequest request);

    List<BookingRequest>
    getAllBookingRequests();

    BookingRequest
    getBookingRequestById(
            int requestId);

    boolean approveBookingRequest(
            int requestId);

    boolean rejectBookingRequest(
            int requestId);
    List<BookingRequest> getBookingRequestsByUserId(int userId);
}