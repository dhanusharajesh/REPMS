<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

    <title>Login</title>

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

    <h1>Login</h1>

    <form action="login"
          method="post">

        <input type="hidden"
               name="propertyId"
               value="<%= request.getParameter("propertyId") %>">

        <input type="text"
               name="username"
               placeholder="Username"
               required>

        <input type="password"
               name="password"
               placeholder="Password"
               required>

        <button type="submit">
            Login
        </button>

    </form>

</div>

<footer>
    © 2026 REPMS | Property Management Platform
</footer>

</body>
</html>
