<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.LeaseRequest" %>
<%@ page import="entity.Tenant" %>
<%@ page import="entity.Property" %>
<%@ page import="entity.User" %>

<html>

<head>
    <title>Request Details</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<header class="top-header">

    <div class="logo"
         onclick="window.location.href='admin-dashboard'">

        <img src="images/logo.png"
             alt="Logo">

        <span>REPMS</span>

    </div>

    <div class="header-title">
        Real Estate Property Management System
    </div>

</header>

<%
    LeaseRequest leaseRequest =
            (LeaseRequest) request.getAttribute(
                    "leaseRequest");

    Tenant tenant =
            (Tenant) request.getAttribute(
                    "tenant");

    Property property =
            (Property) request.getAttribute(
                    "property");

    User tenantUser =
            (User) request.getAttribute(
                    "tenantUser");
%>

<div class="booking-container">

    <h1>Request Details</h1>

    <div class="details-section">

        <h2>Tenant Information</h2>

        <div class="detail-grid">

            <div>
                <strong>Username</strong><br>
                <%= tenantUser.getUsername() %>
            </div>

            <div>
                <strong>Tenant Name</strong><br>
                <%= tenant.getFirstName() %>
                <%= tenant.getLastName() %>
            </div>

        </div>

    </div>

    <div class="details-section">

        <h2>Property Information</h2>

        <div class="detail-grid">

            <div>
                <strong>Property Name</strong><br>
                <%= property.getPropertyName() %>
            </div>

            <div>
                <strong>Property Type</strong><br>
                <%= property.getPropertyType() %>
            </div>

        </div>

    </div>

    <div class="details-section">

        <h2>Request Information</h2>

        <div class="detail-grid">

            <div>
                <strong>Request Type</strong><br>
                <%= leaseRequest.getRequestType() %>
            </div>

            <div>
                <strong>Status</strong><br>
                <%= leaseRequest.getStatus() %>
            </div>

        </div>

        <div class="reason-box">

            <strong>Reason</strong><br>

            <%= leaseRequest.getReason() %>

        </div>

    </div>

    <%
        if("UPDATE".equalsIgnoreCase(
                leaseRequest.getRequestType())){
    %>

    <div class="details-section">

        <h2>Requested Changes</h2>

        <table class="comparison-table">

            <tr>
                <th>Field</th>
                <th>Current Value</th>
                <th>Requested Value</th>
            </tr>

            <tr>
                <td>Phone</td>
                <td><%= tenant.getPhone() %></td>
                <td><%= leaseRequest.getNewPhone() %></td>
            </tr>

            <tr>
                <td>Email</td>
                <td><%= tenant.getEmail() %></td>
                <td><%= leaseRequest.getNewEmail() %></td>
            </tr>

            <tr>
                <td>Lease End Date</td>
                <td><%= tenant.getLeaseEnd() %></td>
                <td><%= leaseRequest.getNewLeaseEnd() %></td>
            </tr>

        </table>

    </div>

    <%
        }
    %>

    <div class="request-buttons">

        <a href="approve-request?id=<%= leaseRequest.getRequestId() %>">

            <button class="update-btn">
                Approve Request
            </button>

        </a>

        <a href="reject-request?id=<%= leaseRequest.getRequestId() %>">

            <button class="terminate-btn">
                Reject Request
            </button>

        </a>

    </div>

</div>
<footer>
    © 2026 REPMS | Property Management Platform
</footer>

</body>
</html>