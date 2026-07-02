import { useEffect, useState } from "react";
import SellerLayout from "../layouts/SellerLayout";
import "./SellerMaintenance.css";

function SellerMaintenance() {

    const [requests, setRequests] =
        useState([]);

    const [properties, setProperties] =
        useState([]);

    const [showModal, setShowModal] =
        useState(false);

    const [formData, setFormData] =
        useState({
            propertyId: "",
            description: ""
        });
    const user =
        JSON.parse(
            localStorage.getItem("user")
        );

    useEffect(() => {

        fetchProperties();

        fetchRequests();

    }, []);

    const fetchProperties = async () => {

        const response =
            await fetch(
                `http://localhost:8080/REPMS/properties?ownerId=${user.ownerId}`
            );

        const data =
            await response.json();

        setProperties(data);
    };
    const fetchRequests = async () => {

        const response =
            await fetch(
                "http://localhost:8080/REPMS/seller-maintenance",
                {
                    credentials: "include"
                }
            );

        const data =
            await response.json();

        setRequests(data);
    };
    const submitNotice = async () => {

        const form =
            new URLSearchParams();

        form.append(
            "propertyId",
            formData.propertyId
        );

        form.append(
            "description",
            formData.description
        );

        const response =
            await fetch(
                "http://localhost:8080/REPMS/maintenance-request",
                {
                    method: "POST",
                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },
                    body: form,
                    credentials: "include"
                }
            );

        const result =
            await response.json();

        if(result.success){

            fetchRequests();

            setShowModal(false);

            setFormData({
                propertyId: "",
                description: ""
            });
        }
    };
    const completeRequest = async (
        requestId
    ) => {

        await fetch(
            `http://localhost:8080/REPMS/complete-maintenance?id=${requestId}`
        );

        fetchRequests();
    };


    return (
        <SellerLayout>

            <div className="seller-maintenance-page">

                <h1>
                    Maintenance Notices
                </h1>

                <button
                    className="create-notice-btn"
                    onClick={() =>
                        setShowModal(true)
                    }
                >
                    Create Notice
                </button>
                <div className="maintenance-table">

                    <table>

                        <thead>

                        <tr>

                            <th>Property</th>

                            <th>Description</th>

                            <th>Date</th>

                            <th>Status</th>

                            <th>Action</th>

                        </tr>

                        </thead>

                        <tbody>

                        {requests.map(request => (

                            <tr key={request.requestId}>

                                <td>
                                    {request.propertyName}
                                </td>

                                <td>
                                    {request.issueDescription}
                                </td>

                                <td>
                                    {request.requestDate}
                                </td>

                                <td>
                                    <span className={`maintenance-status ${request.status.toLowerCase()}`}>{request.status}</span>

                                </td>

                                <td>

                                    {request.status !==
                                        "COMPLETE" && (

                                            <button
                                                className="complete-btn"
                                                onClick={() =>
                                                    completeRequest(
                                                        request.requestId
                                                    )
                                                }
                                            >
                                                Complete
                                            </button>

                                        )}

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

            </div>

            {showModal && (

                <div className="modal-overlay">

                    <div className="maintenance-modal">

                        <h2>
                            Create Maintenance Notice
                        </h2>

                        <select
                            value={formData.propertyId}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    propertyId:
                                    e.target.value
                                })
                            }
                        >

                            <option value="">
                                Select Property
                            </option>

                            {properties.map(property => (

                                <option
                                    key={
                                        property.propertyId
                                    }
                                    value={
                                        property.propertyId
                                    }
                                >

                                    {property.propertyName}

                                </option>

                            ))}

                        </select>

                        <textarea
                            placeholder="Enter maintenance notice..."
                            value={
                                formData.description
                            }
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    description:
                                    e.target.value
                                })
                            }
                        />

                        <div className="modal-actions">

                            <button
                                className="submit-btn"
                                onClick={submitNotice}
                            >
                                Submit
                            </button>

                            <button
                                className="cancel-btn"
                                onClick={() =>
                                    setShowModal(false)
                                }
                            >
                                Cancel
                            </button>

                        </div>

                    </div>

                </div>

            )}

        </SellerLayout>
    );
}

export default SellerMaintenance;