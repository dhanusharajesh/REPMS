import { useEffect, useState } from "react";

import SellerLayout from "../layouts/SellerLayout";

import "./SellerRequests.css";

function SellerRequests() {

    const [requests, setRequests] =
        useState([]);

    const [bookingRequests,
        setBookingRequests] =
        useState([]);

    const [selectedRequest,
        setSelectedRequest] =
        useState(null);

    const [showModal,
        setShowModal] =
        useState(false);

    useEffect(() => {

        fetchRequests();

        fetchBookingRequests();

    }, []);

    const fetchRequests = async () => {

        const response =
            await fetch(
                "http://localhost:8080/REPMS/seller-requests",
                {
                    credentials: "include"
                }
            );

        const data =
            await response.json();

        setRequests(data);
    };

    const fetchBookingRequests =
        async () => {

            const response =
                await fetch(
                    "http://localhost:8080/REPMS/seller-booking-requests",
                    {
                        credentials: "include"
                    }
                );

            const data =
                await response.json();

            setBookingRequests(data);
        };

    const approveRequest = async (
        requestId
    ) => {

        await fetch(
            `http://localhost:8080/REPMS/approve-request?id=${requestId}`,
            {
                credentials: "include"
            }
        );

        fetchRequests();

        setShowModal(false);
    };

    const approveBookingRequest =
        async (requestId) => {

            await fetch(
                `http://localhost:8080/REPMS/approve-booking-request?id=${requestId}`,
                {
                    credentials: "include"
                }
            );

            fetchBookingRequests();
        };

    const rejectRequest = async (
        requestId
    ) => {

        await fetch(
            `http://localhost:8080/REPMS/reject-request?id=${requestId}`,
            {
                credentials: "include"
            }
        );

        fetchRequests();

        setShowModal(false);
    };

    const rejectBookingRequest =
        async (requestId) => {

            await fetch(
                `http://localhost:8080/REPMS/reject-booking-request?id=${requestId}`,
                {
                    credentials: "include"
                }
            );

            fetchBookingRequests();
        };

    return (

        <SellerLayout>

            <div className="seller-requests-page">

                <h1>
                    Booking Requests
                </h1>

                <div className="requests-table">

                    <table>

                        <thead>

                        <tr>

                            <th>Buyer</th>

                            <th>Property</th>

                            <th>Lease Start</th>

                            <th>Lease End</th>

                            <th>Status</th>

                            <th>Action</th>

                        </tr>

                        </thead>

                        <tbody>

                        {bookingRequests.map(request => (

                            <tr
                                key={request.requestId}
                            >

                                <td>
                                    {request.buyerName}
                                </td>

                                <td>
                                    {request.propertyName}
                                </td>

                                <td>
                                    {request.leaseStart}
                                </td>

                                <td>
                                    {request.leaseEnd}
                                </td>

                                <td>

                    <span
                        className={`status ${request.status.toLowerCase()}`}
                    >

                        {request.status}

                    </span>

                                </td>

                                <td>

                                    {request.status ===
                                    "PENDING" ? (

                                        <>

                                            <button
                                                className="approve-btn"
                                                onClick={() =>
                                                    approveBookingRequest(
                                                        request.requestId
                                                    )
                                                }
                                            >

                                                Approve

                                            </button>

                                            <button
                                                className="reject-btn"
                                                onClick={() =>
                                                    rejectBookingRequest(
                                                        request.requestId
                                                    )
                                                }
                                            >

                                                Reject

                                            </button>

                                        </>

                                    ) : (

                                        <span>

                            Processed

                        </span>

                                    )}

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

                <h1 className="lease-heading">
                    Lease Request
                </h1>

                <div className="requests-table">

                    <table>

                        <thead>

                        <tr>

                            <th>Tenant</th>

                            <th>Property</th>

                            <th>Type</th>

                            <th>Status</th>

                            <th>Date</th>

                            <th>Action</th>

                        </tr>

                        </thead>

                        <tbody>

                        {requests.map(request => (

                            <tr
                                key={request.requestId}
                            >

                                <td>
                                    {request.tenantName}
                                </td>

                                <td>
                                    {request.propertyName}
                                </td>

                                <td>
                                    {request.requestType}
                                </td>

                                <td>

                                    <span
                                        className={`status ${request.status.toLowerCase()}`}
                                    >

                                        {request.status}

                                    </span>

                                </td>

                                <td>

                                    {new Date(
                                        request.createdAt
                                    ).toLocaleDateString(
                                        "en-GB",
                                        {
                                            day:"2-digit",
                                            month:"short",
                                            year:"numeric"
                                        }
                                    )}

                                </td>

                                <td>

                                    <button
                                        className="view-btn"
                                        onClick={() => {

                                            setSelectedRequest(
                                                request
                                            );

                                            setShowModal(
                                                true
                                            );

                                        }}
                                    >

                                        View

                                    </button>

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

                {showModal &&
                    selectedRequest && (

                        <div className="modal-overlay">

                            <div className="request-modal">

                                <h2>
                                    Request Details
                                </h2>

                                <p>

                                    <strong>
                                        Tenant:
                                    </strong>

                                    {" "}

                                    {selectedRequest.tenantName}

                                </p>

                                <p>

                                    <strong>
                                        Property:
                                    </strong>

                                    {" "}

                                    {selectedRequest.propertyName}

                                </p>

                                <p>

                                    <strong>
                                        Type:
                                    </strong>

                                    {" "}

                                    {selectedRequest.requestType}

                                </p>

                                <p>

                                    <strong>
                                        Reason:
                                    </strong>

                                    {" "}

                                    {selectedRequest.reason}

                                </p>

                                {selectedRequest.newPhone && (

                                    <p>

                                        <strong>
                                            New Phone:
                                        </strong>

                                        {" "}

                                        {selectedRequest.newPhone}

                                    </p>

                                )}

                                {selectedRequest.newEmail && (

                                    <p>

                                        <strong>
                                            New Email:
                                        </strong>

                                        {" "}

                                        {selectedRequest.newEmail}

                                    </p>

                                )}

                                {selectedRequest.newLeaseEnd && (

                                    <p>

                                        <strong>
                                            New Lease End:
                                        </strong>

                                        {" "}

                                        {selectedRequest.newLeaseEnd}

                                    </p>

                                )}

                                <p>

                                    <strong>
                                        Status:
                                    </strong>

                                    {" "}

                                    {selectedRequest.status}

                                </p>

                                {selectedRequest.status ===
                                    "PENDING" && (

                                        <div className="modal-actions">

                                            <button
                                                className="approve-btn"
                                                onClick={() =>
                                                    approveRequest(
                                                        selectedRequest.requestId
                                                    )
                                                }
                                            >

                                                Approve

                                            </button>

                                            <button
                                                className="reject-btn"
                                                onClick={() =>
                                                    rejectRequest(
                                                        selectedRequest.requestId
                                                    )
                                                }
                                            >

                                                Reject

                                            </button>

                                        </div>

                                    )}

                                <button
                                    className="close-btn"
                                    onClick={() =>
                                        setShowModal(false)
                                    }
                                >

                                    Close

                                </button>

                            </div>

                        </div>

                    )}

            </div>

        </SellerLayout>

    );
}

export default SellerRequests;