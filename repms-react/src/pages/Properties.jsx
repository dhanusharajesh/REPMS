import { useEffect, useState } from "react";
import "./Properties.css";
import SellerLayout from "../layouts/SellerLayout";
import { useLocation } from "react-router-dom";
import {
    FaMapMarkerAlt,
    FaHome,
    FaRulerCombined,
    FaEdit,
    FaTrash
} from "react-icons/fa";

function Properties() {

    const [properties, setProperties] = useState([]);

    const [showAddModal, setShowAddModal] =
        useState(false);

    const [newProperty, setNewProperty] =
        useState({
            ownerId:
            JSON.parse(
                localStorage.getItem(
                    "user"
                )
            ).ownerId,
            propertyName: "",
            address: "",
            propertyType: "",
            sizeSqft: "",
            rentAmount: "",
            imagePath: ""
        });
    const [successMessage, setSuccessMessage] =
        useState("");

    const [editingProperty, setEditingProperty] =
        useState(null);

    const [showEditModal, setShowEditModal] =
        useState(false);

    const [showDeleteModal, setShowDeleteModal] =
        useState(false);

    const [propertyToDelete, setPropertyToDelete] =
        useState(null);

    const location =
        useLocation();

    useEffect(() => {
        fetchProperties();
    }, []);

    const fetchProperties = async () => {

        const user =
            JSON.parse(
                localStorage.getItem(
                    "user"
                )
            );

        const response =
            await fetch(
                `http://localhost:8080/REPMS/properties?ownerId=${user.ownerId}`
            );

        const data = await response.json();

        setProperties(data);
    };
    const handleAddProperty = async () => {

        const formData = new URLSearchParams();

        formData.append(
            "ownerId",
            newProperty.ownerId
        );

        formData.append(
            "propertyName",
            newProperty.propertyName
        );

        formData.append(
            "address",
            newProperty.address
        );

        formData.append(
            "propertyType",
            newProperty.propertyType
        );

        formData.append(
            "sizeSqft",
            newProperty.sizeSqft
        );

        formData.append(
            "rentAmount",
            newProperty.rentAmount
        );

        formData.append(
            "imagePath",
            newProperty.imagePath
        );

        const response =
            await fetch(
                "http://localhost:8080/REPMS/add-property",
                {
                    method: "POST",
                    body: formData
                }
            );

        if(response.ok){

            setShowAddModal(false);

            fetchProperties();

            setSuccessMessage(
                "Property added successfully"
            );

            setTimeout(() => {

                setSuccessMessage("");

            }, 3000);
        }
    };

    const handleUpdateProperty = async () => {

        const formData =
            new URLSearchParams();

        formData.append(
            "propertyId",
            editingProperty.propertyId
        );

        formData.append(
            "ownerId",
            editingProperty.ownerId
        );

        formData.append(
            "propertyName",
            editingProperty.propertyName
        );

        formData.append(
            "address",
            editingProperty.address
        );

        formData.append(
            "propertyType",
            editingProperty.propertyType
        );

        formData.append(
            "sizeSqft",
            editingProperty.sizeSqft
        );

        formData.append(
            "rentAmount",
            editingProperty.rentAmount
        );

        formData.append(
            "status",
            editingProperty.status
        );

        formData.append(
            "imagePath",
            editingProperty.imagePath
        );

        const response =
            await fetch(
                "http://localhost:8080/REPMS/update-property",
                {
                    method: "POST",
                    body: formData
                }
            );

        if(response.ok){

            setShowEditModal(false);

            fetchProperties();

            setSuccessMessage(
                "Property updated successfully"
            );

            setTimeout(() => {

                setSuccessMessage("");

            }, 3000);
        }
    };

    const handleDeleteProperty = async () => {

        const response =
            await fetch(
                `http://localhost:8080/REPMS/delete-property?id=${propertyToDelete}`
            );

        if(response.ok){

            setShowDeleteModal(false);

            setPropertyToDelete(null);

            fetchProperties();

            setSuccessMessage(
                "Property deleted successfully"
            );

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

        <SellerLayout>

            <div className="properties-page">

                <div className="properties-header">

                    <h1>My Properties</h1>

                    {
                        successMessage && (

                            <div className="success-banner">

                                {successMessage}

                            </div>

                        )
                    }

                    <button
                        onClick={() =>
                            setShowAddModal(true)
                        }
                    >
                        + Add Property
                    </button>

                </div>

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

                                    <h3>{property.propertyName}</h3>

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

                                    <div
                                        className={`status-badge ${property.status}`}
                                    >
                                        {property.status}
                                    </div>

                                </div>

                                <div className="property-actions">

                                    <button
                                        className="edit-btn"
                                        onClick={() => {

                                            setEditingProperty(property);

                                            setShowEditModal(true);

                                        }}
                                    >
                                        <FaEdit />
                                        Edit
                                    </button>

                                    <button
                                        className="delete-btn"
                                        onClick={() => {

                                            setPropertyToDelete(
                                                property.propertyId
                                            );

                                            setShowDeleteModal(true);

                                        }}
                                    >
                                        <FaTrash />
                                        Delete
                                    </button>

                                </div>

                            </div>

                        </div>

                    ))}

                </div>
                {
                    showAddModal && (

                        <div className="modal-overlay">

                            <div className="property-modal">

                                <h2>Add Property</h2>

                                <input
                                    placeholder="Property Name"
                                    onChange={(e)=>
                                        setNewProperty({
                                            ...newProperty,
                                            propertyName:e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Address"
                                    onChange={(e)=>
                                        setNewProperty({
                                            ...newProperty,
                                            address:e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Property Type"
                                    onChange={(e)=>
                                        setNewProperty({
                                            ...newProperty,
                                            propertyType:e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Size Sqft"
                                    onChange={(e)=>
                                        setNewProperty({
                                            ...newProperty,
                                            sizeSqft:e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Rent Amount"
                                    onChange={(e)=>
                                        setNewProperty({
                                            ...newProperty,
                                            rentAmount:e.target.value
                                        })
                                    }
                                />

                                <input
                                    placeholder="Image Path"
                                    onChange={(e)=>
                                        setNewProperty({
                                            ...newProperty,
                                            imagePath:e.target.value
                                        })
                                    }
                                />

                                <div className="modal-buttons">

                                    <button
                                        className="save-btn"
                                        onClick={handleAddProperty}
                                    >
                                        Save Property
                                    </button>

                                    <button
                                        className="cancel-btn"
                                        onClick={() =>
                                            setShowAddModal(false)
                                        }
                                    >
                                        Cancel
                                    </button>

                                </div>

                            </div>

                        </div>

                    )}
                {
                    showEditModal && (

                        <div className="modal-overlay">

                            <div className="property-modal">

                                <h2>Edit Property</h2>

                                <input
                                    value={editingProperty.propertyName}
                                    onChange={(e)=>
                                        setEditingProperty({
                                            ...editingProperty,
                                            propertyName:e.target.value
                                        })
                                    }
                                />

                                <input
                                    value={editingProperty.address}
                                    onChange={(e)=>
                                        setEditingProperty({
                                            ...editingProperty,
                                            address:e.target.value
                                        })
                                    }
                                />

                                <input
                                    value={editingProperty.propertyType}
                                    onChange={(e)=>
                                        setEditingProperty({
                                            ...editingProperty,
                                            propertyType:e.target.value
                                        })
                                    }
                                />

                                <input
                                    value={editingProperty.sizeSqft}
                                    onChange={(e)=>
                                        setEditingProperty({
                                            ...editingProperty,
                                            sizeSqft:e.target.value
                                        })
                                    }
                                />

                                <input
                                    value={editingProperty.rentAmount}
                                    onChange={(e)=>
                                        setEditingProperty({
                                            ...editingProperty,
                                            rentAmount:e.target.value
                                        })
                                    }
                                />

                                <input
                                    value={editingProperty.imagePath}
                                    onChange={(e)=>
                                        setEditingProperty({
                                            ...editingProperty,
                                            imagePath:e.target.value
                                        })
                                    }
                                />

                                <div className="modal-buttons">

                                    <button
                                        className="save-btn"
                                        onClick={
                                            handleUpdateProperty
                                        }
                                    >
                                        Update Property
                                    </button>

                                    <button
                                        className="cancel-btn"
                                        onClick={() =>
                                            setShowEditModal(false)
                                        }
                                    >
                                        Cancel
                                    </button>

                                </div>

                            </div>

                        </div>

                    )}
                {
                    showDeleteModal && (

                        <div className="modal-overlay">

                            <div className="delete-modal">

                                <div className="delete-icon">

                                    <FaTrash />

                                </div>

                                <h2>
                                    Delete Property?
                                </h2>

                                <p>
                                    This action cannot be undone.
                                </p>

                                <div className="modal-buttons">

                                    <button
                                        className="cancel-btn"
                                        onClick={() => {

                                            setShowDeleteModal(false);

                                            setPropertyToDelete(null);

                                        }}
                                    >
                                        Cancel
                                    </button>

                                    <button
                                        className="delete-confirm-btn"
                                        onClick={handleDeleteProperty}
                                    >
                                        Delete
                                    </button>

                                </div>

                            </div>

                        </div>

                    )}
            </div>

        </SellerLayout>

    );
}

export default Properties;