import { useEffect, useState } from "react";
import BuyerLayout from "../layouts/BuyerLayout";
import "./Properties.css";
import { useLocation } from "react-router-dom";

import {
    FaMapMarkerAlt,
    FaHome,
    FaRulerCombined
} from "react-icons/fa";

function BuyerProperties() {

    const [properties, setProperties] = useState([]);

    const [selectedProperty, setSelectedProperty] =
        useState(null);

    const [showDetailsModal, setShowDetailsModal] =
        useState(false);

    const [showBookModal, setShowBookModal] =
        useState(false);

    const [successMessage, setSuccessMessage] =
        useState("");

    const [bookingData, setBookingData] =
        useState({
            firstName: "",
            lastName: "",
            phone: "",
            email: "",
            leaseStart: "",
            leaseEnd: ""
        });
    const location =
        useLocation();

    useEffect(() => {

        fetchProperties();

    }, []);

    const fetchProperties = async () => {

        const response =
            await fetch(
                "http://localhost:8080/REPMS/properties"
            );

        const data =
            await response.json();

        console.log(data);

        setProperties(data);

    };

    const handleBooking = async () => {

        const formData =
            new URLSearchParams();

        formData.append(
            "propertyId",
            selectedProperty.propertyId
        );

        formData.append(
            "firstName",
            bookingData.firstName
        );

        formData.append(
            "lastName",
            bookingData.lastName
        );

        formData.append(
            "phone",
            bookingData.phone
        );

        formData.append(
            "email",
            bookingData.email
        );

        formData.append(
            "leaseStart",
            bookingData.leaseStart
        );

        formData.append(
            "leaseEnd",
            bookingData.leaseEnd
        );

        const response =
            await fetch(
                "http://localhost:8080/REPMS/booking",
                {
                    method: "POST",
                    credentials: "include",
                    body: formData
                }
            );

        console.log(response.status);

        if(response.ok){

            setShowBookModal(false);

            setSuccessMessage(
                "Property booked successfully"
            );

            fetchProperties();

            setTimeout(() => {

                setSuccessMessage("");

            }, 3000);
        }
    };

    const searchTerm =
        new URLSearchParams(
            location.search
        ).get("search") || "";

    const filteredProperties =
        properties.filter(property =>

            property.propertyName
                ?.toLowerCase()
                .includes(
                    searchTerm.toLowerCase()
                )

            ||

            property.address
                ?.toLowerCase()
                .includes(
                    searchTerm.toLowerCase()
                )

            ||

            property.propertyType
                ?.toLowerCase()
                .includes(
                    searchTerm.toLowerCase()
                )
        );

    return (

        <BuyerLayout>

            <div className="properties-page">

                <div className="properties-header">

                    <h1>Available Properties</h1>

                </div>

                {
                    successMessage && (

                        <div className="success-banner">

                            {successMessage}

                        </div>

                    )
                }

                <div className="property-grid">

                    {filteredProperties.map(property => (

                        <div
                            className="property-card"
                            key={property.propertyId}
                        >

                            <img
                                src={`http://localhost:8080/REPMS/${property.imagePath}`}
                                alt={property.propertyName}
                            />

                            <div className="property-body">

                                <div className="property-content">

                                    <h3>
                                        {property.propertyName}
                                    </h3>

                                    <p className="property-address">

                                        <FaMapMarkerAlt />

                                        {property.address}

                                    </p>

                                    <div className="property-features">

                                        <span>

                                            <FaHome />

                                            {property.propertyType}

                                        </span>

                                        <span>

                                            <FaRulerCombined />

                                            {property.sizeSqft} sqft

                                        </span>

                                    </div>

                                    <div className="property-price">

                                        ₹{property.rentAmount}

                                    </div>

                                    <div className="owner-info">

                                        <p>
                                            <strong>Owner:</strong>
                                            {" "}
                                            {property.ownerName}
                                        </p>

                                        <p>
                                            <strong>Phone:</strong>
                                            {" "}
                                            {property.ownerPhone}
                                        </p>

                                    </div>

                                    <div
                                        className={`status-badge ${property.status.toLowerCase()}`}
                                    >

                                        {property.status?.toUpperCase()}

                                    </div>

                                </div>

                                <div className="property-actions">

                                    <button
                                        className="edit-btn"
                                        onClick={() => {

                                            setSelectedProperty(property);

                                            setShowDetailsModal(true);

                                        }}
                                    >
                                        View Details
                                    </button>

                                    {
                                        property.status?.toLowerCase() === "available"
                                            ? (
                                                <button
                                                    className="save-btn"
                                                    onClick={() => {

                                                        setSelectedProperty(property);

                                                        setShowBookModal(true);

                                                    }}
                                                >
                                                    Book Now
                                                </button>
                                            )
                                            : (
                                                <button
                                                    className="delete-btn"
                                                    disabled
                                                >
                                                    Rented
                                                </button>
                                            )
                                    }

                                </div>

                            </div>

                        </div>

                    ))}

                </div>

                {/* DETAILS MODAL */}

                {
                    showDetailsModal &&
                    selectedProperty && (

                        <div className="modal-overlay">

                            <div className="property-modal">

                                <h2>
                                    Property Details
                                </h2>

                                <img
                                    src={`http://localhost:8080/REPMS/${selectedProperty.imagePath}`}
                                    alt=""
                                    className="map-image"
                                />

                                <p>
                                    <strong>Name:</strong>{" "}
                                    {selectedProperty.propertyName}
                                </p>

                                <p>
                                    <strong>Address:</strong>{" "}
                                    {selectedProperty.address}
                                </p>

                                <p>
                                    <strong>Type:</strong>{" "}
                                    {selectedProperty.propertyType}
                                </p>

                                <p>
                                    <strong>Size:</strong>{" "}
                                    {selectedProperty.sizeSqft} sqft
                                </p>

                                <p>
                                    <strong>Rent:</strong> ₹
                                    {selectedProperty.rentAmount}
                                </p>

                                <p>
                                    <strong>Owner:</strong>
                                    {" "}
                                    {selectedProperty.ownerName}
                                </p>

                                <p>
                                    <strong>Phone:</strong>
                                    {" "}
                                    {selectedProperty.ownerPhone}
                                </p>

                                <p>
                                    <strong>Status:</strong>{" "}
                                    {selectedProperty.status}
                                </p>

                                <button
                                    className="cancel-btn"
                                    onClick={() =>
                                        setShowDetailsModal(false)
                                    }
                                >
                                    Close
                                </button>

                            </div>

                        </div>

                    )
                }

                {/* BOOK MODAL */}

                {
                    showBookModal &&
                    selectedProperty && (

                        <div className="modal-overlay">

                            <div className="property-modal">

                                <h2>
                                    Book Property
                                </h2>

                                <input
                                    placeholder="First Name"
                                    onChange={(e) =>
                                        setBookingData({
                                            ...bookingData,
                                            firstName:
                                            e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Last Name"
                                    onChange={(e) =>
                                        setBookingData({
                                            ...bookingData,
                                            lastName:
                                            e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Phone"
                                    onChange={(e) =>
                                        setBookingData({
                                            ...bookingData,
                                            phone:
                                            e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Email"
                                    onChange={(e) =>
                                        setBookingData({
                                            ...bookingData,
                                            email:
                                            e.target.value
                                        })
                                    }
                                />

                                <label>
                                    Lease Start
                                </label>

                                <input
                                    type="date"
                                    onChange={(e) =>
                                        setBookingData({
                                            ...bookingData,
                                            leaseStart:
                                            e.target.value
                                        })
                                    }
                                />

                                <label>
                                    Lease End
                                </label>

                                <input
                                    type="date"
                                    onChange={(e) =>
                                        setBookingData({
                                            ...bookingData,
                                            leaseEnd:
                                            e.target.value
                                        })
                                    }
                                />

                                <div className="modal-buttons">

                                    <button
                                        className="save-btn"
                                        onClick={
                                            handleBooking
                                        }
                                    >
                                        Submit Request
                                    </button>

                                    <button
                                        className="cancel-btn"
                                        onClick={() =>
                                            setShowBookModal(false)
                                        }
                                    >
                                        Cancel
                                    </button>

                                </div>

                            </div>

                        </div>

                    )
                }

            </div>

        </BuyerLayout>

    );
}

export default BuyerProperties;