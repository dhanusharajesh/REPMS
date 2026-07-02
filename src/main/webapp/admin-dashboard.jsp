<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.User" %>

<html>

<head>
    <title>Admin Dashboard</title>
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

            <li class="active">
                Dashboard
            </li>

            <li onclick="window.location.href='admin-requests'">
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

        <h2>Admin Dashboard</h2>

        <div class="stats-container">

            <div class="stat-card total-card">
                <h3>Total Properties</h3>
                <p>${totalProperties}</p>
            </div>

            <div class="stat-card available-card">
                <h3>Available Properties</h3>
                <p>${availableProperties}</p>
            </div>

            <div class="stat-card rented-card">
                <h3>Rented Properties</h3>
                <p>${rentedProperties}</p>
            </div>

            <div class="stat-card tenant-card">
                <h3>Total Tenants</h3>
                <p>${totalTenants}</p>
            </div>

            <div class="stat-card request-card">
                <h3>Pending Requests</h3>
                <p>${pendingRequests}</p>
            </div>

        </div>

    </main>

</div>

<footer>
    © 2026 REPMS | Property Management Platform
</footer>

</body>
</html>