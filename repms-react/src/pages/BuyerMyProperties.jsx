import { useEffect, useState } from "react";
import BuyerLayout from "../layouts/BuyerLayout";
import {
    FiMapPin,
    FiHome,
    FiMaximize
} from "react-icons/fi";

import "./BuyerMyProperties.css";

function BuyerMyProperties() {

    const [properties, setProperties] =
        useState([]);

    const [selectedProperty,
        setSelectedProperty] =
        useState(null);

    const [showDetails,
        setShowDetails] =
        useState(false);

    const [showUpdate,
        setShowUpdate] =
        useState(false);

    const [showTerminate,
        setShowTerminate] =
        useState(false);

    const [requestData,
        setRequestData] =
        useState({
            reason: "",
            newPhone: "",
            newEmail: "",
            newLeaseEnd: ""
        });
    const [showSuccessModal,
        setShowSuccessModal] =
        useState(false);

    const [successMessage,
        setSuccessMessage] =
        useState("");
    const user =
        JSON.parse(
            localStorage.getItem("user")
        );

    useEffect(() => {

        fetchProperties();

    }, []);

    const fetchProperties = async () => {

        const response =
            await fetch(
                `http://localhost:8080/REPMS/my-properties?userId=${user.userId}`
            );

        const data =
            await response.json();

        setProperties(data);
    };

    const submitUpdateRequest = async () => {

        const formData =
            new URLSearchParams();

        formData.append(
            "requestType",
            "UPDATE"
        );

        formData.append(
            "newPhone",
            requestData.newPhone
        );

        formData.append(
            "newEmail",
            requestData.newEmail
        );

        formData.append(
            "newLeaseEnd",
            requestData.newLeaseEnd
        );

        formData.append(
            "reason",
            requestData.reason
        );

        formData.append(
            "propertyId",
            selectedProperty.propertyId
        );

        console.log("Submitting update request...");
        console.log(requestData);

        const response =
            await fetch(
                "http://localhost:8080/REPMS/lease-request",
                {
                    method: "POST",
                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },
                    body: formData,
                    credentials: "include"
                }
            );

        console.log(response);

        if(response.ok){

            setSuccessMessage(
                "Update request submitted successfully."
            );

            setShowSuccessModal(true);

            setShowUpdate(false);
        }
    };

    const submitTerminateRequest = async () => {

        const formData =
            new URLSearchParams();

        formData.append(
            "requestType",
            "TERMINATION"
        );

        formData.append(
            "reason",
            requestData.reason
        );

        formData.append(
            "propertyId",
            selectedProperty.propertyId
        );

        const response =
            await fetch(
                "http://localhost:8080/REPMS/lease-request",
                {
                    method: "POST",
                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },
                    body: formData,
                    credentials: "include"
                }
            );

        if(response.ok){

            setSuccessMessage(
                "Termination request submitted successfully."
            );

            setShowSuccessModal(true);

            setShowTerminate(false);
        }
    };

    return (

        <BuyerLayout>

            <div className="my-properties-page">

                <h1>
                    My Properties
                </h1>

                <div className="property-grid">

                    {properties.map(property => (

                        <div
                            key={property.propertyId}
                            className="property-card"
                        >

                            <img
                                src={`/${property.imagePath}`}
                                alt={property.propertyName}
                            />

                            <div className="property-body">

                                <h3>
                                    {property.propertyName}
                                </h3>

                                <div className="property-address">

                                    <FiMapPin />

                                    {property.address}

                                </div>

                                <div className="property-features">

                                    <span>

                                        <FiHome />

                                        {property.propertyType}

                                    </span>

                                    <span>

                                        <FiMaximize />

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
                                    className={`status-badge ${
                                        property.status === "rented"
                                            ? "rented"
                                            : "available"
                                    }`}
                                >

                                    {property.status === "rented"
                                        ? "ACTIVE LEASE"
                                        : "AVAILABLE"}

                                </div>

                                <div className="property-actions">

                                    <button
                                        className="details-btn"
                                        onClick={() => {

                                            setSelectedProperty(
                                                property
                                            );

                                            setShowDetails(
                                                true
                                            );
                                        }}
                                    >
                                        View Details
                                    </button>

                                    {property.status === "rented" && (

                                        <>
                                            <button
                                                className="update-btn"
                                                onClick={() => {

                                                    setSelectedProperty(
                                                        property
                                                    );

                                                    setShowUpdate(
                                                        true
                                                    );
                                                }}
                                            >
                                                Update Lease
                                            </button>

                                            <button
                                                className="terminate-btn"
                                                onClick={() => {

                                                    setSelectedProperty(
                                                        property
                                                    );

                                                    setShowTerminate(
                                                        true
                                                    );
                                                }}
                                            >
                                                Terminate
                                            </button>
                                        </>

                                    )}

                                </div>

                            </div>

                        </div>

                    ))}

                </div>

            </div>

            {
                showDetails && selectedProperty && (

                    <div className="modal-overlay">

                        <div className="property-modal">

                            <h2>
                                {selectedProperty.propertyName}
                            </h2>

                            <img
                                src={`/${selectedProperty.imagePath}`}
                                alt=""
                                style={{
                                    width:"100%",
                                    borderRadius:"10px"
                                }}
                            />

                            <p>
                                Address:
                                {" "}
                                {selectedProperty.address}
                            </p>

                            <p>
                                Type:
                                {" "}
                                {selectedProperty.propertyType}
                            </p>

                            <p>
                                Size:
                                {" "}
                                {selectedProperty.sizeSqft}
                                sqft
                            </p>

                            <p>
                                Rent:
                                ₹{selectedProperty.rentAmount}
                            </p>

                            <p>
                                Owner:
                                {" "}
                                {selectedProperty.ownerName}
                            </p>

                            <p>
                                Phone:
                                {" "}
                                {selectedProperty.ownerPhone}
                            </p>

                            <button
                                className="save-btn"
                                onClick={() =>
                                    setShowDetails(false)
                                }
                            >
                                Close
                            </button>

                        </div>

                    </div>

                )}
            {
                showUpdate && (

                    <div className="modal-overlay">

                        <div className="property-modal">

                            <h2>
                                Update Lease Request
                            </h2>

                            <input
                                placeholder="New Phone"
                                onChange={(e)=>
                                    setRequestData({
                                        ...requestData,
                                        newPhone:e.target.value
                                    })
                                }
                            />

                            <input
                                placeholder="New Email"
                                onChange={(e)=>
                                    setRequestData({
                                        ...requestData,
                                        newEmail:e.target.value
                                    })
                                }
                            />

                            <input
                                type="date"
                                onChange={(e)=>
                                    setRequestData({
                                        ...requestData,
                                        newLeaseEnd:e.target.value
                                    })
                                }
                            />

                            <textarea
                                placeholder="Reason"
                                onChange={(e)=>
                                    setRequestData({
                                        ...requestData,
                                        reason:e.target.value
                                    })
                                }
                            />

                            <div className="modal-buttons">

                                <button
                                    className="cancel-btn"
                                    onClick={() =>
                                        setShowUpdate(false)
                                    }
                                >
                                    Cancel
                                </button>

                                <button
                                    className="save-btn"
                                    onClick={
                                        submitUpdateRequest
                                    }
                                >
                                    Submit
                                </button>

                            </div>

                        </div>

                    </div>

                )}
            {
                showTerminate && (

                    <div className="modal-overlay">

                        <div className="delete-modal">

                            <h2>
                                Terminate Lease
                            </h2>

                            <textarea
                                placeholder="Reason"
                                onChange={(e)=>
                                    setRequestData({
                                        ...requestData,
                                        reason:e.target.value
                                    })
                                }
                            />

                            <div className="modal-buttons">

                                <button
                                    className="cancel-btn"
                                    onClick={() =>
                                        setShowTerminate(false)
                                    }
                                >
                                    Cancel
                                </button>

                                <button
                                    className="delete-confirm-btn"
                                    onClick={
                                        submitTerminateRequest
                                    }
                                >
                                    Submit Request
                                </button>

                            </div>

                        </div>

                    </div>

                )}
            {showSuccessModal && (

                <div className="modal-overlay">

                    <div className="success-modal">

                        <div className="success-icon">
                            ✓
                        </div>

                        <h2>
                            Success
                        </h2>

                        <p>
                            {successMessage}
                        </p>

                        <button
                            className="success-btn"
                            onClick={() =>
                                setShowSuccessModal(false)
                            }
                        >
                            OK
                        </button>

                    </div>

                </div>

            )}

        </BuyerLayout>

    );
}

export default BuyerMyProperties;