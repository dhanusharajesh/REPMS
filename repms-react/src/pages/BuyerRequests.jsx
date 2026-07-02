import { useEffect, useState } from "react";

import BuyerLayout from "../layouts/BuyerLayout";

import "./BuyerRequests.css";

function BuyerRequests() {

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

    const user =
        JSON.parse(
            localStorage.getItem("user")
        );

    useEffect(() => {

        fetchRequests();

        fetchBookingRequests();

    }, []);

    const fetchRequests = async () => {

        const response =
            await fetch(
                `http://localhost:8080/REPMS/my-requests?userId=${user.userId}`
            );

        const data =
            await response.json();

        setRequests(data);
    };

    const fetchBookingRequests =
        async () => {

            const userId =
                localStorage.getItem(
                    "userId"
                );

            const response =
                await fetch(
                    `http://localhost:8080/REPMS/buyer-booking-requests?userId=${userId}`,
                    {
                        credentials: "include"
                    }
                );

            const data =
                await response.json();

            setBookingRequests(data);
        };

    return (

        <BuyerLayout>

            <div className="requests-page">

                <h1
                    style={{
                        marginTop: "50px"
                    }}
                >
                    Booking Requests
                </h1>

                <div className="requests-table">

                    <table>

                        <thead>

                        <tr>

                            <th>Property</th>

                            <th>Lease Start</th>

                            <th>Lease End</th>

                            <th>Status</th>

                        </tr>

                        </thead>

                        <tbody>

                        {bookingRequests.map(
                            request => (

                                <tr
                                    key={
                                        request.requestId
                                    }
                                >

                                    <td>
                                        {
                                            request.propertyName
                                        }
                                    </td>

                                    <td>
                                        {
                                            request.leaseStart
                                        }
                                    </td>

                                    <td>
                                        {
                                            request.leaseEnd
                                        }
                                    </td>

                                    <td>

                        <span
                            className={`status ${request.status.toLowerCase()}`}
                        >

                            {
                                request.status
                            }

                        </span>

                                    </td>

                                </tr>

                            )
                        )}

                        </tbody>

                    </table>

                </div>

                <h1
                    style={{
                        marginTop: "50px"
                    }}
                >
                    My Requests
                </h1>

                <div className="requests-table">

                    <table>

                        <thead>

                        <tr>

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
                                    {request.requestType}
                                </td>

                                <td>

                                    <span
                                        className={`status ${request.status?.toLowerCase()}`}
                                    >

                                        {request.status}

                                    </span>

                                </td>

                                <td>
                                    {new Date(
                                        request.createdAt
                                    ).toLocaleDateString(
                                        "en-GB"
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

                                <button
                                    className="close-btn"
                                    onClick={() =>
                                        setShowModal(
                                            false
                                        )
                                    }
                                >

                                    Close

                                </button>

                            </div>

                        </div>

                    )}

            </div>

        </BuyerLayout>

    );
}

export default BuyerRequests;