<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Property" %>
<%@ page import="entity.User" %>

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

            <li class="active"
                onclick="window.location.href='properties'">
                Dashboard
            </li>

            <li
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

        <h2>Available Properties</h2>

        <div class="property-container">

            <%
                List<Property> properties =
                        (List<Property>) request.getAttribute("properties");

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
                        if(property.getStatus().equalsIgnoreCase("available")){
                    %>

                    <%
                        if(loggedUser != null){
                    %>

                    <a href="booking?propertyId=<%= property.getPropertyId() %>">

                        <button class="book-btn">
                            Book Now
                        </button>

                    </a>

                    <%
                    } else {
                    %>

                    <a href="login.jsp?propertyId=<%= property.getPropertyId() %>">

                        <button class="book-btn">
                            Book Now
                        </button>

                    </a>

                    <%
                        }
                    %>

                    <%
                    } else {
                    %>

                    <button class="booked-btn">
                        Booked
                    </button>

                    <%
                        }
                    %>

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