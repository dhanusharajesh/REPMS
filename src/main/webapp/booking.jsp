<%@ page import="entity.Property" %>
<%@ page import="entity.User" %>

<%
    Property property =
            (Property) request.getAttribute("property");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Book Property</title>

    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<header class="top-header">

    <div class="logo"
         onclick="window.location.href='properties'">

        <img src="images/logo.png"
             alt="REPMS Logo">

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

        <a href="logout" class="logout-btn">
            Logout
        </a>

        <%
            }
        %>

    </div>

    <div class="header-title">
        Real Estate Property Management System
    </div>

</header>

<div class="booking-container">

    <h1>Tenant Information</h1>

    <div class="selected-property">

        <h2>Selected Property</h2>

        <div class="property-summary-card">

            <img src="<%= request.getContextPath() %>/<%= property.getImagePath() %>"
                 alt="Property">

            <div class="property-summary-info">

                <h3>
                    <%= property.getPropertyName() %>
                </h3>

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
                    &#8377;<%= property.getRentAmount() %>
                </p>

                <p>
                    <strong>Status:</strong>
                    <%= property.getStatus() %>
                </p>

            </div>

        </div>

    </div>

    <form action="booking"
          method="post">

        <input type="hidden"
               name="propertyId"
               value="<%= property.getPropertyId() %>">

        <input type="text"
               name="firstName"
               placeholder="First Name"
               required>

        <input type="text"
               name="lastName"
               placeholder="Last Name"
               required>

        <input type="tel"
               name="phone"
               placeholder="Phone Number"
               required>

        <input type="email"
               name="email"
               placeholder="Email Address"
               required>

        <label>Lease Start Date</label>

        <input type="date"
               name="leaseStart"
               required>

        <label>Lease End Date</label>

        <input type="date"
               name="leaseEnd"
               required>

        <button type="submit">
            Confirm Booking
        </button>

    </form>

</div>

<footer>
    © 2026 REPMS | Property Management Platform
</footer>

</body>
</html>