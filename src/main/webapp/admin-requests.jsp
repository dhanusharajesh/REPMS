<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.LeaseRequest" %>
<%@ page import="entity.User" %>

<html>

<head>
    <title>Lease Requests</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<header class="top-header">

    <div class="logo">
        <img src="images/logo.png" alt="Logo">
        <span>REPMS</span>
    </div>

    <div class="user-info">

        <%
            User loggedUser =
                    (User) session.getAttribute("user");
        %>

        Welcome,
        <%= loggedUser.getUsername() %>

        <a href="logout"
           class="logout-btn">
            Logout
        </a>

    </div>

    <div class="header-title">
        Real Estate Property Management System
    </div>

</header>

<div class="dashboard-layout">

    <aside class="sidebar">

        <h2>Menu</h2>

        <ul>

            <li onclick="window.location.href='admin-dashboard'">
                Dashboard
            </li>

            <li class="active">
                Requests
            </li>

            <li onclick="window.location.href='admin-properties'">
                Properties
            </li>

            <li>
                Payments
            </li>

            <li>
                Maintenance
            </li>

        </ul>

    </aside>

    <main class="main-content">

        <h2>Lease Requests</h2>

        <table class="request-table">

            <tr>
                <th>ID</th>
                <th>Tenant ID</th>
                <th>Type</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Created</th>
                <th>Actions</th>
            </tr>

            <%
                List<LeaseRequest> requests =
                        (List<LeaseRequest>)
                                request.getAttribute("requests");

                if(requests != null){

                    for(LeaseRequest req : requests){
            %>

            <tr>

                <td>
                    <%= req.getRequestId() %>
                </td>

                <td>
                    <%= req.getTenantId() %>
                </td>

                <td>
                    <%= req.getRequestType() %>
                </td>

                <td>
                    <%= req.getReason() %>
                </td>

                <td>
                    <span class="<%=
                    "PENDING".equalsIgnoreCase(req.getStatus())
                    ? "status-pending"
                    : "APPROVED".equalsIgnoreCase(req.getStatus())
                    ? "status-approved"
                    : "status-rejected"
                    %>">

                    <%= req.getStatus() %>

                    </span>

                </td>

                <td>
                    <%= req.getCreatedAt() %>
                </td>

                <td>

                    <a href="request-details?id=<%= req.getRequestId() %>">

                        <button class="update-btn">
                            View Details
                        </button>

                    </a>

                </td>

            </tr>

            <%
                    }
                }
            %>

        </table>

    </main>

</div>

<footer>
    © 2026 REPMS | Property Management Platform
</footer>

</body>
</html>