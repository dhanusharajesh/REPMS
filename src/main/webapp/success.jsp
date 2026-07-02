<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.User" %>

<html>
<head>

    <title>Booking Successful</title>

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

<div class="success-container">

    <div class="success-card">

        <h1>
            Booking Successful
        </h1>

        <p>
            Tenant has been assigned successfully.
        </p>

        <p>
            Property status has been updated to
            <strong>Rented</strong>.
        </p>

        <button
                onclick="window.location.href='properties'">

            Back To Properties

        </button>

    </div>

</div>

<footer>

    © 2026 REPMS | Property Management Platform

</footer>

</body>
</html>