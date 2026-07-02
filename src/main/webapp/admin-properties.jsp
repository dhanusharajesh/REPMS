<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Property" %>
<%@ page import="entity.User" %>

<html>

<head>
    <title>Property Management</title>
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

            <li onclick="window.location.href='admin-requests'">
                Requests
            </li>

            <li class="active">
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

        <h2>Property Management</h2>
        <%
            String success =
                    request.getParameter("success");

            if("property-added".equals(success)){
        %>

        <div class="success-message">
            Property added successfully.
        </div>

        <%
            }

            if("property-updated".equals(success)){
        %>

        <div class="success-message">
            Property updated successfully.
        </div>

        <%
            }

            if("property-deleted".equals(success)){
        %>

        <div class="success-message">
            Property deleted successfully.
        </div>

        <%
            }
        %>
        <div style="margin-bottom:20px;">

            <button
                    class="add-property-btn"
                    onclick="document.getElementById('addPropertyForm').style.display='block'">

                + Add Property

            </button>

        </div>
        <div id="addPropertyForm"
             style="display:none;
            background:#eab6b0;
            padding:20px;
            border-radius:10px;
            margin-bottom:20px;">

            <h3>Add New Property</h3>

            <form action="add-property"
                  method="post">

                <div class="form-group">

                    <label>Owner</label>

                    <select name="ownerId" required>

                        <option value="1">
                            Dhanusha Rajesh
                        </option>

                        <option value="3">
                            Akshaya Ashok
                        </option>

                    </select>

                </div>

                <div class="form-group">

                    <label>Property Name</label>

                    <input type="text"
                           name="propertyName"
                           required>

                </div>

                <div class="form-group">

                    <label>Address</label>

                    <input type="text"
                           name="address"
                           required>

                </div>

                <div class="form-group">

                    <label>Property Type</label>

                    <input type="text"
                           name="propertyType"
                           required>

                </div>

                <div class="form-group">

                    <label>Size (sqft)</label>

                    <input type="number"
                           step="0.01"
                           name="sizeSqft"
                           required>

                </div>

                <div class="form-group">

                    <label>Rent Amount</label>

                    <input type="number"
                           step="0.01"
                           name="rentAmount"
                           required>

                </div>

                <div class="form-group">

                    <label>Property Image</label>

                    <select name="imagePath" required>

                        <option value="images/apartment1.jpg">
                            Apartment Image
                        </option>

                        <option value="images/flat1.jpg">
                            Flat Image
                        </option>

                        <option value="images/house1.jpg">
                            House Image
                        </option>

                        <option value="images/villa1.jpg">
                            Villa Image
                        </option>

                    </select>

                </div>

                <button type="submit"
                        class="book-btn">

                    Save Property

                </button>

            </form>

        </div>

        <table class="request-table">

            <thead>

            <tr>
                <th>ID</th>
                <th>Property Name</th>
                <th>Type</th>
                <th>Address</th>
                <th>Size (sqft)</th>
                <th>Rent</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>

            </thead>

            <tbody>

            <%
                List<Property> properties =
                        (List<Property>) request.getAttribute("properties");

                if(properties != null){

                    for(Property property : properties){
            %>

            <tr>

                <td>
                    <%= property.getPropertyId() %>
                </td>

                <td>
                    <%= property.getPropertyName() %>
                </td>

                <td>
                    <%= property.getPropertyType() %>
                </td>

                <td>
                    <%= property.getAddress() %>
                </td>

                <td>
                    <%= property.getSizeSqft() %>
                </td>

                <td>
                    ₹ <%= property.getRentAmount() %>
                </td>

                <td>
                    <%= property.getStatus() %>
                </td>

                <td>

                    <a href="edit-property?id=<%= property.getPropertyId() %>">

                        <button class="approve-btn">

                            Edit

                        </button>

                    </a>

                    <button
                            class="reject-btn"
                            onclick="openDeleteModal(<%= property.getPropertyId() %>)">

                        Delete

                    </button>

                </td>

            </tr>

            <%
                    }
                }
            %>

            </tbody>

        </table>

        <div id="deleteModal"
             class="delete-modal">

            <div class="delete-modal-content">

                <h3>Delete Property</h3>

                <p>
                    Are you sure you want to delete this property?
                </p>

                <div class="modal-buttons">

                    <button
                            class="modal-cancel-btn"
                            onclick="closeDeleteModal()">

                        Cancel

                    </button>

                    <a id="confirmDeleteBtn"
                       href="#">

                        <button class="modal-delete-btn">

                            Delete

                        </button>

                    </a>

                </div>

            </div>

        </div>

    </main>

</div>

<footer>
    © 2026 REPMS | Property Management Platform
</footer>

<script>

    function openDeleteModal(propertyId){

        document
            .getElementById("deleteModal")
            .style.display = "flex";

        document
            .getElementById("confirmDeleteBtn")
            .href =
            "delete-property?id=" + propertyId;
    }

    function closeDeleteModal(){

        document
            .getElementById("deleteModal")
            .style.display = "none";
    }

</script>

</body>
</html>