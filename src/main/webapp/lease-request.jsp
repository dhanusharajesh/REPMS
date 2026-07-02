<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <title>Lease Request</title>

    <link rel="stylesheet"
          href="css/style.css">

</head>

<body>

<header class="top-header">

    <div class="logo"
         onclick="window.location.href='properties'">

        <img src="images/logo.png"
             alt="Logo">

        <span>REPMS</span>

    </div>

    <div class="header-title">
        Real Estate Property Management System
    </div>

</header>

<div class="booking-container">

    <%
        String requestType =
                (String) request.getAttribute(
                        "requestType");
    %>

    <h1>
        <%= requestType %> Request
    </h1>

    <form action="lease-request"
          method="post"
          class="lease-request-form">

        <input type="hidden"
               name="requestType"
               value="<%= requestType %>">

        <p>
            Request Type:
            <strong><%= requestType %></strong>
        </p>

        <%
            if("UPDATE".equalsIgnoreCase(requestType)){
        %>

        <div class="form-group">
            <label>New Phone Number</label>
            <input type="text"
                   name="newPhone"
                   placeholder="Enter new phone number">
        </div>

        <div class="form-group">
            <label>New Email Address</label>
            <input type="email"
                   name="newEmail"
                   placeholder="Enter new email address">
        </div>

        <div class="form-group">
            <label>New Lease End Date</label>
            <input type="date"
                   name="newLeaseEnd">
        </div>

        <div class="form-group">
            <label>Reason for Update</label>
            <textarea
                    name="reason"
                    placeholder="Explain why you are requesting this update"
                    required></textarea>
        </div>

        <%
        } else {
        %>

        <div class="form-group">
            <label>Reason for Termination</label>
            <textarea
                    name="reason"
                    placeholder="Explain why you want to terminate the lease"
                    required></textarea>
        </div>

        <%
            }
        %>

        <button type="submit">
            Submit Request
        </button>

    </form>

</div>

<footer>
    © 2026 REPMS | Property Management Platform
</footer>

</body>
</html>