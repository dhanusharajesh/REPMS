<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Property" %>

<%
    Property property =
            (Property) request.getAttribute("property");
%>

<html>

<head>
    <title>Edit Property</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<div class="booking-container">

    <h1>Edit Property</h1>

    <form action="update-property"
          method="post">

        <input type="hidden"
               name="propertyId"
               value="<%= property.getPropertyId() %>">

        <div class="form-group">

            <label>Owner</label>

            <select name="ownerId">

                <option value="1"
                        <%= property.getOwnerId() == 1 ? "selected" : "" %>>
                    Dhanusha Rajesh
                </option>

                <option value="3"
                        <%= property.getOwnerId() == 3 ? "selected" : "" %>>
                    Akshaya Ashok
                </option>

            </select>

        </div>

        <div class="form-group">

            <label>Property Name</label>

            <input type="text"
                   name="propertyName"
                   value="<%= property.getPropertyName() %>"
                   required>

        </div>

        <div class="form-group">

            <label>Address</label>

            <input type="text"
                   name="address"
                   value="<%= property.getAddress() %>"
                   required>

        </div>

        <div class="form-group">

            <label>Property Type</label>

            <input type="text"
                   name="propertyType"
                   value="<%= property.getPropertyType() %>"
                   required>

        </div>

        <div class="form-group">

            <label>Size (sqft)</label>

            <input type="number"
                   step="0.01"
                   name="sizeSqft"
                   value="<%= property.getSizeSqft() %>"
                   required>

        </div>

        <div class="form-group">

            <label>Rent Amount</label>

            <input type="number"
                   step="0.01"
                   name="rentAmount"
                   value="<%= property.getRentAmount() %>"
                   required>

        </div>

        <div class="form-group">

            <label>Status</label>

            <select name="status">

                <option value="available"
                        <%= "available".equals(property.getStatus())
                                ? "selected"
                                : "" %>>
                    Available
                </option>

                <option value="rented"
                        <%= "rented".equals(property.getStatus())
                                ? "selected"
                                : "" %>>
                    Rented
                </option>

            </select>

        </div>

        <div class="form-group">

            <label>Property Image</label>

            <select name="imagePath">

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

            Update Property

        </button>

    </form>

</div>

</body>

</html>