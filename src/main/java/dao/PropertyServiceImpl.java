package dao;
import entity.*;
import util.DBConnUtil;
import util.DBPropertyUtil;
import java.sql.*;
import exception.OwnerNotFoundException;
import exception.PropertyNotFoundException;
import exception.TenantNotFoundException;
import exception.PaymentProcessingException;
import exception.MaintenanceRequestException;
import java.util.List;
import java.util.ArrayList;
import entity.User;
import entity.LeaseRequest;

public class PropertyServiceImpl implements PropertyService {

    @Override
    public boolean addOwner(Owner owner) {

        String query =
                "INSERT INTO owners(first_name, last_name, phone, email, address) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setString(1, owner.getFirstName());
            ps.setString(2, owner.getLastName());
            ps.setString(3, owner.getPhone());
            ps.setString(4, owner.getEmail());
            ps.setString(5, owner.getAddress());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateOwner(Owner owner) {

        String query =
                "UPDATE owners " +
                        "SET first_name = ?, last_name = ?, phone = ?, email = ?, address = ? " +
                        "WHERE owner_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setString(1, owner.getFirstName());
            pstmt.setString(2, owner.getLastName());
            pstmt.setString(3, owner.getPhone());
            pstmt.setString(4, owner.getEmail());
            pstmt.setString(5, owner.getAddress());
            pstmt.setInt(6, owner.getOwnerId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOwner(int ownerId) {

        String query =
                "DELETE FROM owners WHERE owner_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, ownerId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Owner getOwnerById(int ownerId)
            throws OwnerNotFoundException {

        String query =
                "SELECT * FROM owners WHERE owner_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, ownerId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Owner owner = new Owner();

                owner.setOwnerId(
                        rs.getInt("owner_id"));

                owner.setFirstName(
                        rs.getString("first_name"));

                owner.setLastName(
                        rs.getString("last_name"));

                owner.setPhone(
                        rs.getString("phone"));

                owner.setEmail(
                        rs.getString("email"));

                owner.setAddress(
                        rs.getString("address"));

                return owner;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new OwnerNotFoundException(
                "Owner with ID " + ownerId + " not found");
    }

    @Override
    public boolean addProperty(Property property) {

        String query =
                "INSERT INTO properties(owner_id, property_name, address, property_type, size_sqft, rent_amount, status, image_path) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, property.getOwnerId());
            ps.setString(2, property.getPropertyName());
            ps.setString(3, property.getAddress());
            ps.setString(4, property.getPropertyType());
            ps.setDouble(5, property.getSizeSqft());
            ps.setDouble(6, property.getRentAmount());
            ps.setString(7, property.getStatus());
            ps.setString(8, property.getImagePath());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateProperty(Property property) {

        String query =
                "UPDATE properties " +
                        "SET owner_id = ?, property_name = ?, address = ?, property_type = ?, size_sqft = ?, rent_amount = ?, status = ?, image_path = ? " +
                        "WHERE property_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, property.getOwnerId());
            pstmt.setString(2, property.getPropertyName());
            pstmt.setString(3, property.getAddress());
            pstmt.setString(4, property.getPropertyType());
            pstmt.setDouble(5, property.getSizeSqft());
            pstmt.setDouble(6, property.getRentAmount());
            pstmt.setString(7, property.getStatus());
            pstmt.setString(8, property.getImagePath());
            pstmt.setInt(9, property.getPropertyId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProperty(int propertyId) {

        String query =
                "DELETE FROM properties WHERE property_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, propertyId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Property getPropertyById(int propertyId)
            throws PropertyNotFoundException {

        String query =
                "SELECT * FROM properties WHERE property_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, propertyId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Property property = new Property();

                property.setPropertyId(
                        rs.getInt("property_id"));

                property.setOwnerId(
                        rs.getInt("owner_id"));

                property.setPropertyName(
                        rs.getString("property_name"));

                property.setAddress(
                        rs.getString("address"));

                property.setPropertyType(
                        rs.getString("property_type"));

                property.setSizeSqft(
                        rs.getDouble("size_sqft"));

                property.setRentAmount(
                        rs.getDouble("rent_amount"));

                property.setStatus(
                        rs.getString("status"));

                property.setImagePath(
                        rs.getString("image_path"));

                return property;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new PropertyNotFoundException(
                "Property with ID " + propertyId + " not found");
    }

    @Override
    public List<Property> getAllProperties() {

        List<Property> properties = new ArrayList<>();

        String query = "SELECT * FROM properties";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");
            System.out.println(
                    "CONNECTION STRING = " + connectionString);

            Connection conn =
                    DBConnUtil.getConnection(connectionString);
            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Property property = new Property(
                        rs.getInt("property_id"),
                        rs.getInt("owner_id"),
                        rs.getString("property_name"),
                        rs.getString("address"),
                        rs.getString("property_type"),
                        rs.getDouble("size_sqft"),
                        rs.getDouble("rent_amount"),
                        rs.getString("status")
                );

                property.setImagePath(
                        rs.getString("image_path"));

                properties.add(property);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public List<Property> getPropertiesByUserId(int userId) {

        List<Property> properties = new ArrayList<>();

        String query =
                "SELECT p.* " +
                        "FROM properties p " +
                        "JOIN tenants t ON p.property_id = t.property_id " +
                        "WHERE t.user_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();
            while (rs.next()) {

                Property property = new Property();

                property.setPropertyId(
                        rs.getInt("property_id"));

                property.setOwnerId(
                        rs.getInt("owner_id"));

                property.setPropertyName(
                        rs.getString("property_name"));

                property.setAddress(
                        rs.getString("address"));

                property.setPropertyType(
                        rs.getString("property_type"));

                property.setSizeSqft(
                        rs.getDouble("size_sqft"));

                property.setRentAmount(
                        rs.getDouble("rent_amount"));

                property.setStatus(
                        rs.getString("status"));

                property.setImagePath(
                        rs.getString("image_path"));

                properties.add(property);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public List<Property> getPropertiesByOwnerId(
            int ownerId) {

        List<Property> properties =
                new ArrayList<>();

        String query =
                "SELECT * FROM properties " +
                        "WHERE owner_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, ownerId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                Property property =
                        new Property();

                property.setPropertyId(
                        rs.getInt("property_id"));

                property.setOwnerId(
                        rs.getInt("owner_id"));

                property.setPropertyName(
                        rs.getString("property_name"));

                property.setAddress(
                        rs.getString("address"));

                property.setPropertyType(
                        rs.getString("property_type"));

                property.setSizeSqft(
                        rs.getDouble("size_sqft"));

                property.setRentAmount(
                        rs.getDouble("rent_amount"));

                property.setStatus(
                        rs.getString("status"));

                property.setImagePath(
                        rs.getString("image_path"));

                properties.add(property);
            }

        } catch(Exception e){

            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public boolean addTenant(Tenant tenant) {

        String query =
                "INSERT INTO tenants(property_id, user_id, first_name, last_name, phone, email, lease_start, lease_end) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, tenant.getPropertyId());
            ps.setInt(2, tenant.getUserId());
            ps.setString(3, tenant.getFirstName());
            ps.setString(4, tenant.getLastName());
            ps.setString(5, tenant.getPhone());
            ps.setString(6, tenant.getEmail());
            ps.setDate(7, tenant.getLeaseStart());
            ps.setDate(8, tenant.getLeaseEnd());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateTenant(Tenant tenant) {

        String query =
                "UPDATE tenants " +
                        "SET property_id = ?, first_name = ?, last_name = ?, phone = ?, email = ?, lease_start = ?, lease_end = ? " +
                        "WHERE tenant_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, tenant.getPropertyId());
            pstmt.setString(2, tenant.getFirstName());
            pstmt.setString(3, tenant.getLastName());
            pstmt.setString(4, tenant.getPhone());
            pstmt.setString(5, tenant.getEmail());

            pstmt.setDate(6, tenant.getLeaseStart());
            pstmt.setDate(7, tenant.getLeaseEnd());

            pstmt.setInt(8, tenant.getTenantId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteTenant(int tenantId) {

        String query =
                "DELETE FROM tenants WHERE tenant_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, tenantId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Tenant getTenantById(int tenantId)
            throws TenantNotFoundException {

        String query =
                "SELECT * FROM tenants WHERE tenant_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, tenantId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Tenant tenant = new Tenant();

                tenant.setTenantId(
                        rs.getInt("tenant_id"));

                tenant.setPropertyId(
                        rs.getInt("property_id"));

                tenant.setUserId(
                        rs.getInt("user_id"));

                tenant.setFirstName(
                        rs.getString("first_name"));

                tenant.setLastName(
                        rs.getString("last_name"));

                tenant.setPhone(
                        rs.getString("phone"));

                tenant.setEmail(
                        rs.getString("email"));

                tenant.setLeaseStart(
                        rs.getDate("lease_start"));

                tenant.setLeaseEnd(
                        rs.getDate("lease_end"));

                return tenant;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new TenantNotFoundException(
                "Tenant with ID " + tenantId + " not found");
    }

    @Override
    public Tenant getTenantByUserId(int userId)
            throws TenantNotFoundException {

        String query =
                "SELECT * FROM tenants WHERE user_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Tenant tenant = new Tenant();

                tenant.setTenantId(
                        rs.getInt("tenant_id"));

                tenant.setPropertyId(
                        rs.getInt("property_id"));

                tenant.setUserId(
                        rs.getInt("user_id"));

                tenant.setFirstName(
                        rs.getString("first_name"));

                tenant.setLastName(
                        rs.getString("last_name"));

                tenant.setPhone(
                        rs.getString("phone"));

                tenant.setEmail(
                        rs.getString("email"));

                tenant.setLeaseStart(
                        rs.getDate("lease_start"));

                tenant.setLeaseEnd(
                        rs.getDate("lease_end"));

                return tenant;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new TenantNotFoundException(
                "Tenant not found for user ID "
                        + userId);
    }

    @Override
    public boolean assignLease(Tenant tenant) {

        try {

            boolean tenantAdded = addTenant(tenant);

            if (!tenantAdded) {
                return false;
            }

            Property property =
                    getPropertyById(tenant.getPropertyId());

            if (property == null) {
                return false;
            }

            property.setStatus("rented");

            return updateProperty(property);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateLease(Tenant tenant) {
        return updateTenant(tenant);
    }

    @Override
    public boolean terminateLease(int tenantId) {

        try {

            Tenant tenant =
                    getTenantById(tenantId);

            if (tenant == null) {
                return false;
            }

            int propertyId =
                    tenant.getPropertyId();

            boolean tenantDeleted =
                    deleteTenant(tenantId);

            if (!tenantDeleted) {
                return false;
            }

            Property property =
                    getPropertyById(propertyId);

            if (property == null) {
                return false;
            }

            property.setStatus("available");

            return updateProperty(property);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean createLeaseRequest(
            LeaseRequest request) {

        String query =
                "INSERT INTO lease_requests " +
                        "(tenant_id, property_id, request_type, new_phone, new_email, new_lease_end, reason) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(
                    1,
                    request.getTenantId());

            ps.setInt(
                    2,
                    request.getPropertyId());

            ps.setString(
                    3,
                    request.getRequestType());

            ps.setString(
                    4,
                    request.getNewPhone());

            ps.setString(
                    5,
                    request.getNewEmail());

            ps.setDate(
                    6,
                    request.getNewLeaseEnd());

            ps.setString(
                    7,
                    request.getReason());

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<LeaseRequest> getAllLeaseRequests() {

        List<LeaseRequest> requests =
                new ArrayList<>();

        String query =
                "SELECT * FROM lease_requests " +
                        "ORDER BY created_at DESC";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                LeaseRequest request =
                        new LeaseRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setTenantId(
                        rs.getInt("tenant_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                request.setRequestType(
                        rs.getString("request_type"));

                request.setNewPhone(
                        rs.getString("new_phone"));

                request.setNewEmail(
                        rs.getString("new_email"));

                request.setNewLeaseEnd(
                        rs.getDate("new_lease_end"));

                request.setReason(
                        rs.getString("reason"));

                request.setStatus(
                        rs.getString("status"));

                request.setCreatedAt(
                        rs.getTimestamp("created_at"));

                requests.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public List<LeaseRequest> getLeaseRequestsByTenantId(
            int tenantId) {

        List<LeaseRequest> requests =
                new ArrayList<>();

        String query =
                "SELECT * FROM lease_requests " +
                        "WHERE tenant_id = ? " +
                        "ORDER BY created_at DESC";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, tenantId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                LeaseRequest request =
                        new LeaseRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setTenantId(
                        rs.getInt("tenant_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                request.setRequestType(
                        rs.getString("request_type"));

                request.setNewPhone(
                        rs.getString("new_phone"));

                request.setNewEmail(
                        rs.getString("new_email"));

                request.setNewLeaseEnd(
                        rs.getDate("new_lease_end"));

                request.setReason(
                        rs.getString("reason"));

                request.setStatus(
                        rs.getString("status"));

                request.setCreatedAt(
                        rs.getTimestamp("created_at"));

                requests.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public LeaseRequest getLeaseRequestById(int requestId) {

        String query =
                "SELECT * FROM lease_requests " +
                        "WHERE request_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, requestId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                LeaseRequest request =
                        new LeaseRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setTenantId(
                        rs.getInt("tenant_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                request.setRequestType(
                        rs.getString("request_type"));

                request.setNewPhone(
                        rs.getString("new_phone"));

                request.setNewEmail(
                        rs.getString("new_email"));

                request.setNewLeaseEnd(
                        rs.getDate("new_lease_end"));

                request.setReason(
                        rs.getString("reason"));

                request.setStatus(
                        rs.getString("status"));

                request.setCreatedAt(
                        rs.getTimestamp("created_at"));

                return request;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getTotalProperties() {

        String query =
                "SELECT COUNT(*) FROM properties";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getAvailablePropertiesCount() {

        String query =
                "SELECT COUNT(*) FROM properties WHERE status='available'";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getRentedPropertiesCount() {

        String query =
                "SELECT COUNT(*) FROM properties WHERE status='rented'";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getTotalTenants() {

        String query =
                "SELECT COUNT(*) FROM tenants";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getPendingRequestsCount() {

        String query =
                "SELECT COUNT(*) FROM lease_requests WHERE status='PENDING'";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean processPayment(Payment payment)
            throws PaymentProcessingException {

        String query =
                "INSERT INTO payments(tenant_id, amount, payment_date, payment_method, payment_status) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, payment.getTenantId());
            ps.setDouble(2, payment.getAmount());
            ps.setDate(3, payment.getPaymentDate());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getPaymentStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            throw new PaymentProcessingException(
                    "Payment processing failed");
        }
    }

    @Override
    public boolean updatePayment(Payment payment) {

        String query =
                "UPDATE payments " +
                        "SET tenant_id = ?, amount = ?, payment_date = ?, payment_method = ?, payment_status = ? " +
                        "WHERE payment_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, payment.getTenantId());
            pstmt.setDouble(2, payment.getAmount());
            pstmt.setDate(3, payment.getPaymentDate());
            pstmt.setString(4, payment.getPaymentMethod());
            pstmt.setString(5, payment.getPaymentStatus());

            pstmt.setInt(6, payment.getPaymentId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePayment(int paymentId) {

        String query =
                "DELETE FROM payments WHERE payment_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, paymentId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Payment getPaymentById(int paymentId) {

        String query =
                "SELECT * FROM payments WHERE payment_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, paymentId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(
                        rs.getInt("payment_id"));

                payment.setTenantId(
                        rs.getInt("tenant_id"));

                payment.setAmount(
                        rs.getDouble("amount"));

                payment.setPaymentDate(
                        rs.getDate("payment_date"));

                payment.setPaymentMethod(
                        rs.getString("payment_method"));

                payment.setPaymentStatus(
                        rs.getString("payment_status"));

                return payment;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean logRequest(MaintenanceRequest request)
            throws MaintenanceRequestException {

        String query =
                "INSERT INTO maintenance_requests(property_id, tenant_id, issue_description, request_date, status) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, request.getPropertyId());

            if (request.getTenantId() == null) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, request.getTenantId());
            }

            ps.setString(3, request.getIssueDescription());
            ps.setDate(4, request.getRequestDate());
            ps.setString(5, request.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            throw new MaintenanceRequestException(
                    "Failed to log maintenance request");
        }
    }

    @Override
    public boolean updateRequest(MaintenanceRequest request) {

        String query =
                "UPDATE maintenance_requests " +
                        "SET property_id = ?, tenant_id = ?, issue_description = ?, request_date = ?, status = ? " +
                        "WHERE request_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, request.getPropertyId());

            if (request.getTenantId() == null) {
                pstmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(2, request.getTenantId());
            }

            pstmt.setString(3, request.getIssueDescription());
            pstmt.setDate(4, request.getRequestDate());
            pstmt.setString(5, request.getStatus());

            pstmt.setInt(6, request.getRequestId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteRequest(int requestId) {

        String query =
                "DELETE FROM maintenance_requests WHERE request_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, requestId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public MaintenanceRequest getRequestById(int requestId) {

        String query =
                "SELECT * FROM maintenance_requests WHERE request_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString("src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, requestId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                MaintenanceRequest request =
                        new MaintenanceRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                int tenantId =
                        rs.getInt("tenant_id");

                if (rs.wasNull()) {
                    request.setTenantId(null);
                } else {
                    request.setTenantId(tenantId);
                }

                request.setIssueDescription(
                        rs.getString("issue_description"));

                request.setRequestDate(
                        rs.getDate("request_date"));

                request.setStatus(
                        rs.getString("status"));

                return request;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<MaintenanceRequest>
    getAllMaintenanceRequests() {

        List<MaintenanceRequest> requests =
                new ArrayList<>();

        String query =
                "SELECT * FROM maintenance_requests " +
                        "ORDER BY request_date DESC";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                MaintenanceRequest request =
                        new MaintenanceRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                int tenantId =
                        rs.getInt("tenant_id");

                if(rs.wasNull()){

                    request.setTenantId(null);

                }else{

                    request.setTenantId(
                            tenantId);
                }

                request.setIssueDescription(
                        rs.getString(
                                "issue_description"));

                request.setRequestDate(
                        rs.getDate(
                                "request_date"));

                request.setStatus(
                        rs.getString(
                                "status"));

                requests.add(request);
            }

        } catch(Exception e){

            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public boolean completeMaintenanceRequest(
            int requestId) {

        String query =
                "UPDATE maintenance_requests " +
                        "SET status='COMPLETE' " +
                        "WHERE request_id=?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, requestId);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch(Exception e){

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User validateUser(String username,
                             String password) {

        String query =
                "SELECT * FROM users " +
                        "WHERE username = ? " +
                        "AND password = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserId(
                        rs.getInt("user_id"));

                user.setUsername(
                        rs.getString("username"));

                user.setPassword(
                        rs.getString("password"));

                user.setRole(
                        rs.getString("role"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserById(int userId) {

        String query =
                "SELECT * FROM users WHERE user_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                User user =
                        new User();

                user.setUserId(
                        rs.getInt("user_id"));

                user.setUsername(
                        rs.getString("username"));

                user.setPassword(
                        rs.getString("password"));

                user.setRole(
                        rs.getString("role"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getOwnerIdByUserId(int userId) {

        String query =
                "SELECT owner_id " +
                        "FROM owners " +
                        "WHERE user_id = ?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement pstmt =
                    conn.prepareStatement(query);

            pstmt.setInt(1, userId);

            ResultSet rs =
                    pstmt.executeQuery();

            if(rs.next()) {

                return rs.getInt("owner_id");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;
    }

    @Override
    public boolean approveUpdateRequest(
            int requestId) {

        try {

            LeaseRequest request =
                    getLeaseRequestById(
                            requestId);

            Tenant tenant =
                    getTenantById(
                            request.getTenantId());

            String phone =
                    (request.getNewPhone() == null ||
                            request.getNewPhone().trim().isEmpty())
                            ? tenant.getPhone()
                            : request.getNewPhone();

            String email =
                    (request.getNewEmail() == null ||
                            request.getNewEmail().trim().isEmpty())
                            ? tenant.getEmail()
                            : request.getNewEmail();

            java.sql.Date leaseEnd =
                    (request.getNewLeaseEnd() == null)
                            ? tenant.getLeaseEnd()
                            : request.getNewLeaseEnd();

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            conn.setAutoCommit(false);

            String updateTenant =
                    "UPDATE tenants " +
                            "SET phone = ?, email = ?, lease_end = ? " +
                            "WHERE tenant_id = ?";

            PreparedStatement ps1 =
                    conn.prepareStatement(
                            updateTenant);

            ps1.setString(
                    1,
                    phone);

            ps1.setString(
                    2,
                    email);

            ps1.setDate(
                    3,
                    leaseEnd);

            ps1.setInt(
                    4,
                    tenant.getTenantId());

            ps1.executeUpdate();

            String updateRequest =
                    "UPDATE lease_requests " +
                            "SET status='APPROVED' " +
                            "WHERE request_id=?";

            PreparedStatement ps2 =
                    conn.prepareStatement(
                            updateRequest);

            ps2.setInt(
                    1,
                    requestId);

            ps2.executeUpdate();

            conn.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean approveTerminationRequest(
            int requestId) {

        try {

            LeaseRequest request =
                    getLeaseRequestById(
                            requestId);

            Tenant tenant =
                    getTenantById(
                            request.getTenantId());

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            conn.setAutoCommit(false);

            String updateProperty =
                    "UPDATE properties " +
                            "SET status='available' " +
                            "WHERE property_id=?";

            PreparedStatement ps1 =
                    conn.prepareStatement(
                            updateProperty);

            ps1.setInt(
                    1,
                    request.getPropertyId());

            ps1.executeUpdate();

            String deleteRequests =
                    "DELETE FROM lease_requests " +
                            "WHERE tenant_id=?";

            PreparedStatement ps2 =
                    conn.prepareStatement(
                            deleteRequests);

            ps2.setInt(
                    1,
                    tenant.getTenantId());

            ps2.executeUpdate();

            String deleteTenant =
                    "DELETE FROM tenants " +
                            "WHERE tenant_id=?";

            PreparedStatement ps3 =
                    conn.prepareStatement(
                            deleteTenant);

            ps3.setInt(
                    1,
                    tenant.getTenantId());

            ps3.executeUpdate();

            conn.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean rejectRequest(
            int requestId) {

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            String query =
                    "UPDATE lease_requests " +
                            "SET status='REJECTED' " +
                            "WHERE request_id=?";

            PreparedStatement ps =
                    conn.prepareStatement(
                            query);

            ps.setInt(
                    1,
                    requestId);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean createBookingRequest(
            BookingRequest request) {

        String query =
                "INSERT INTO booking_requests " +
                        "(property_id, user_id, first_name, last_name, phone, email, lease_start, lease_end) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, request.getPropertyId());
            ps.setInt(2, request.getUserId());
            ps.setString(3, request.getFirstName());
            ps.setString(4, request.getLastName());
            ps.setString(5, request.getPhone());
            ps.setString(6, request.getEmail());
            ps.setDate(7, request.getLeaseStart());
            ps.setDate(8, request.getLeaseEnd());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch(Exception e){

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<BookingRequest>
    getAllBookingRequests() {

        List<BookingRequest> requests =
                new ArrayList<>();

        String query =
                "SELECT * FROM booking_requests " +
                        "ORDER BY created_at DESC";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                BookingRequest request =
                        new BookingRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                request.setUserId(
                        rs.getInt("user_id"));

                request.setFirstName(
                        rs.getString("first_name"));

                request.setLastName(
                        rs.getString("last_name"));

                request.setPhone(
                        rs.getString("phone"));

                request.setEmail(
                        rs.getString("email"));

                request.setLeaseStart(
                        rs.getDate("lease_start"));

                request.setLeaseEnd(
                        rs.getDate("lease_end"));

                request.setStatus(
                        rs.getString("status"));

                request.setCreatedAt(
                        rs.getTimestamp("created_at"));

                requests.add(request);
            }

        } catch(Exception e){

            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public BookingRequest
    getBookingRequestById(
            int requestId) {

        String query =
                "SELECT * FROM booking_requests " +
                        "WHERE request_id=?";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, requestId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                BookingRequest request =
                        new BookingRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                request.setUserId(
                        rs.getInt("user_id"));

                request.setFirstName(
                        rs.getString("first_name"));

                request.setLastName(
                        rs.getString("last_name"));

                request.setPhone(
                        rs.getString("phone"));

                request.setEmail(
                        rs.getString("email"));

                request.setLeaseStart(
                        rs.getDate("lease_start"));

                request.setLeaseEnd(
                        rs.getDate("lease_end"));

                request.setStatus(
                        rs.getString("status"));

                return request;
            }

        } catch(Exception e){

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean approveBookingRequest(
            int requestId) {

        try {

            BookingRequest request =
                    getBookingRequestById(
                            requestId);

            Tenant tenant =
                    new Tenant();

            tenant.setPropertyId(
                    request.getPropertyId());

            tenant.setUserId(
                    request.getUserId());

            tenant.setFirstName(
                    request.getFirstName());

            tenant.setLastName(
                    request.getLastName());

            tenant.setPhone(
                    request.getPhone());

            tenant.setEmail(
                    request.getEmail());

            tenant.setLeaseStart(
                    request.getLeaseStart());

            tenant.setLeaseEnd(
                    request.getLeaseEnd());

            boolean leaseCreated =
                    assignLease(tenant);

            if(!leaseCreated){

                return false;
            }

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(
                            "UPDATE booking_requests " +
                                    "SET status='APPROVED' " +
                                    "WHERE request_id=?");

            ps.setInt(1, requestId);

            ps.executeUpdate();

            return true;

        } catch(Exception e){

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean rejectBookingRequest(
            int requestId) {

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(
                            "UPDATE booking_requests " +
                                    "SET status='REJECTED' " +
                                    "WHERE request_id=?");

            ps.setInt(1, requestId);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch(Exception e){

            e.printStackTrace();
        }

        return false;
    }
    @Override
    public List<BookingRequest>
    getBookingRequestsByUserId(
            int userId) {

        List<BookingRequest> requests =
                new ArrayList<>();

        String query =
                "SELECT * FROM booking_requests " +
                        "WHERE user_id = ? " +
                        "ORDER BY created_at DESC";

        try {

            String connectionString =
                    DBPropertyUtil.getConnectionString(
                            "src/main/resources/db.properties");

            Connection conn =
                    DBConnUtil.getConnection(
                            connectionString);

            PreparedStatement ps =
                    conn.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                BookingRequest request =
                        new BookingRequest();

                request.setRequestId(
                        rs.getInt("request_id"));

                request.setPropertyId(
                        rs.getInt("property_id"));

                request.setUserId(
                        rs.getInt("user_id"));

                request.setFirstName(
                        rs.getString("first_name"));

                request.setLastName(
                        rs.getString("last_name"));

                request.setPhone(
                        rs.getString("phone"));

                request.setEmail(
                        rs.getString("email"));

                request.setLeaseStart(
                        rs.getDate("lease_start"));

                request.setLeaseEnd(
                        rs.getDate("lease_end"));

                request.setStatus(
                        rs.getString("status"));

                request.setCreatedAt(
                        rs.getTimestamp("created_at"));

                requests.add(request);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return requests;
    }
}