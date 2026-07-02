import { useEffect, useState } from "react";

import BuyerLayout from "../layouts/BuyerLayout";

import {
    FiMail,
    FiPhone,
    FiHome,
    FiKey,
    FiFileText,
    FiTool,
    FiDollarSign,
    FiCalendar,
    FiShield
} from "react-icons/fi";

import "./BuyerProfile.css";

function BuyerProfile() {

    const [profile,
        setProfile] =
        useState(null);

    useEffect(() => {

        fetchProfile();

    }, []);

    const fetchProfile =
        async () => {

            const response =
                await fetch(
                    "http://localhost:8080/REPMS/buyer-profile",
                    {
                        credentials: "include"
                    }
                );

            const data =
                await response.json();

            setProfile(data);
        };

    if(!profile){

        return (
            <BuyerLayout>
                <h2>Loading...</h2>
            </BuyerLayout>
        );
    }

    return (

        <BuyerLayout>

            <div className="buyer-profile-page">

                <div className="buyer-profile-header">

                    <img
                        src={`https://ui-avatars.com/api/?name=${profile.firstName}+${profile.lastName}`}
                        alt="Profile"
                    />

                    <div>

                        <h1>

                            {profile.firstName}
                            {" "}
                            {profile.lastName}

                        </h1>

                        <p>
                            @{profile.username}
                        </p>

                        <span className="buyer-role-badge">

                            {profile.role}

                        </span>

                    </div>

                </div>

                <div className="profile-grid">

                    {/* Personal */}

                    <div className="profile-card personal-card">

                        <h3>
                            Personal Information
                        </h3>

                        <div className="detail-row">
                            <FiMail />
                            <span>Email</span>
                            <strong>{profile.email}</strong>
                        </div>

                        <div className="detail-row">
                            <FiPhone />
                            <span>Phone</span>
                            <strong>{profile.phone}</strong>
                        </div>

                        <div className="detail-row">
                            <FiHome />
                            <span>Property</span>
                            <strong>{profile.propertyId}</strong>
                        </div>

                        <div className="detail-row">
                            <FiKey />
                            <span>Tenant ID</span>
                            <strong>{profile.tenantId}</strong>
                        </div>

                    </div>

                    {/* Statistics */}

                    <div className="profile-card stats-card">

                        <h3>
                            Lease Statistics
                        </h3>

                        <div className="stats-mini-grid">

                            <div className="mini-stat">

                                <FiHome />

                                <h2>
                                    {profile.totalProperties}
                                </h2>

                                <span>
                                    Properties
                                </span>

                            </div>

                            <div className="mini-stat">

                                <FiKey />

                                <h2>
                                    {profile.activeLeases}
                                </h2>

                                <span>
                                    Leases
                                </span>

                            </div>

                            <div className="mini-stat">

                                <FiFileText />

                                <h2>
                                    {profile.pendingRequests}
                                </h2>

                                <span>
                                    Requests
                                </span>

                            </div>

                            <div className="mini-stat">

                                <FiTool />

                                <h2>
                                    {profile.maintenanceRequests}
                                </h2>

                                <span>
                                    Maintenance
                                </span>

                            </div>

                        </div>

                    </div>

                    {/* Summary */}

                    <div className="profile-card business-card">

                        <h3>
                            Buyer Summary
                        </h3>

                        <div className="business-row">

                            <div className="business-left">

                                <div className="business-icon">
                                    <FiDollarSign />
                                </div>

                                <span>
                                    Monthly Rent
                                </span>

                            </div>

                            <strong>
                                ₹45,000
                            </strong>

                        </div>

                        <div className="business-row">

                            <div className="business-left">

                                <div className="business-icon">
                                    <FiCalendar />
                                </div>

                                <span>
                                    Member Since
                                </span>

                            </div>

                            <strong>
                                Jan 2025
                            </strong>

                        </div>

                        <div className="business-row">

                            <div className="business-left">

                                <div className="business-icon">
                                    <FiShield />
                                </div>

                                <span>
                                    Account Status
                                </span>

                            </div>

                            <strong
                                className="active-status"
                            >
                                Active
                            </strong>

                        </div>

                    </div>

                </div>

            </div>

        </BuyerLayout>

    );
}

export default BuyerProfile;