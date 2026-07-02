<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Property" %>
<%@ page import="entity.User" %>
<%@ page import="entity.Tenant" %>

<html>
<head>
    <title>REPMS Properties</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<header class="top-header">

    <div class="logo"
         onclick="window.location.href='properties'">
        <img src="images/logo.png" alt="Logo">
        <span>REPMS</span>
    </div>

    <div class="user-info">

        <%
            User loggedUser =
                    (User) session.getAttribute("user");

            if(loggedUser != null){
        %>

        Welcome,
        <%= loggedUser.getUsername() %>

        <a href="logout"
           class="logout-btn">
            Logout
        </a>

        <%
        } else {
        %>

        <a href="login.jsp"
           class="logout-btn">
            Login
        </a>

        <%
            }
        %>

    </div>

    <div class="header-title">
        Real Estate Property Management System
    </div>

</header>

<div class="dashboard-layout">

    <aside class="sidebar">

        <h2>Menu</h2>

        <ul>
            <li
                onclick="window.location.href='properties'">
                Dashboard
            </li>

            <li class="active"
                onclick="window.location.href='my-properties'">
                My Properties
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

        <h2>My Properties</h2>

        <%
            String success =
                    request.getParameter("success");

            if("request-submitted".equals(success)){
        %>

        <div class="success-message">
            Request submitted successfully.
        </div>

        <%
            }
        %>

        <div class="property-container">

            <%
                List<Property> properties =
                        (List<Property>) request.getAttribute("properties");

                Tenant tenant =
                        (Tenant) request.getAttribute("tenant");

                if(properties != null){

                    for(Property property : properties){
            %>

            <div class="property-card">
                <img src="<%= property.getImagePath() %>"
                     alt="Property">

                <div class="property-info">

                    <h2><%= property.getPropertyName() %></h2>

                    <p>
                        <strong>Type:</strong>
                        <%= property.getPropertyType() %>
                    </p>

                    <p>
                        <strong>Address:</strong>
                        <%= property.getAddress() %>
                    </p>

                    <p>
                        <strong>Rent:</strong>
                        ₹<%= property.getRentAmount() %>
                    </p>

                    <p>
                        <strong>Status:</strong>
                        <%= property.getStatus() %>
                    </p>

                    <%
                        if(tenant != null){
                    %>

                    <p>
                        <strong>Lease Start:</strong>
                        <%= tenant.getLeaseStart() %>
                    </p>

                    <p>
                        <strong>Lease End:</strong>
                        <%= tenant.getLeaseEnd() %>
                    </p>

                    <%
                        }
                    %>

                    <div class="request-buttons">

                        <a href="lease-request?type=UPDATE">
                            <button class="update-btn">
                                Request Update
                            </button>
                        </a>

                        <a href="lease-request?type=TERMINATION">
                            <button class="terminate-btn">
                                Request Termination
                            </button>
                        </a>

                    </div>

                </div>

            </div>

            <%
                    }
                }
            %>

        </div>

    </main>

</div>
<footer>
    © 2026 REPMS | Property Management Platform
</footer>
</body>
</html>