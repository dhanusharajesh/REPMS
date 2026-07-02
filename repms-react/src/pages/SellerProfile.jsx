import { useEffect, useState } from "react";

import SellerLayout from "../layouts/SellerLayout";

import "./SellerProfile.css";
import {
    FiMail,
    FiPhone,
    FiMapPin,
    FiUser,
    FiHome,
    FiCheckCircle,
    FiKey,
    FiFileText,
    FiDollarSign,
    FiCalendar,
    FiShield
} from "react-icons/fi";

function SellerProfile() {

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
                    "http://localhost:8080/REPMS/seller-profile",
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
            <SellerLayout>
                <h2>Loading...</h2>
            </SellerLayout>
        );
    }

    return (

        <SellerLayout>

            <div className="seller-profile-page">

                <div className="profile-header">

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

                        <span className="role-badge">

                            {profile.role}

                        </span>

                    </div>

                </div>

                <div className="profile-grid">

                    <div className="profile-card personal-card">

                        <h3>Personal Information</h3>

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
                            <FiMapPin />
                            <span>Address</span>
                            <strong>{profile.address}</strong>
                        </div>

                        <div className="detail-row">
                            <FiUser />
                            <span>Owner ID</span>
                            <strong>{profile.ownerId}</strong>
                        </div>

                    </div>

                    <div className="profile-card stats-card">

                        <h3>Property Statistics</h3>

                        <div className="stats-mini-grid">

                            <div className="mini-stat">
                                <FiHome />
                                <h2>{profile.totalProperties}</h2>
                                <span>Total</span>
                            </div>

                            <div className="mini-stat">
                                <FiCheckCircle />
                                <h2>{profile.availableProperties}</h2>
                                <span>Available</span>
                            </div>

                            <div className="mini-stat">
                                <FiKey />
                                <h2>{profile.rentedProperties}</h2>
                                <span>Rented</span>
                            </div>

                            <div className="mini-stat">
                                <FiFileText />
                                <h2>{profile.totalRequests}</h2>
                                <span>Requests</span>
                            </div>

                        </div>

                    </div>

                    <div className="profile-card business-card">

                        <h3>Business Summary</h3>

                        <div className="business-row">

                            <div className="business-left">

                                <div className="business-icon">
                                    <FiDollarSign />
                                </div>

                                <span>Revenue This Month</span>

                            </div>

                            <strong>₹45,000</strong>

                        </div>

                        <div className="business-row">

                            <div className="business-left">

                                <div className="business-icon">
                                    <FiCalendar />
                                </div>

                                <span>Member Since</span>

                            </div>

                            <strong>Jan 2025</strong>

                        </div>

                        <div className="business-row">

                            <div className="business-left">

                                <div className="business-icon">
                                    <FiShield />
                                </div>

                                <span>Account Status</span>

                            </div>

                            <strong className="active-status">
                                Active
                            </strong>

                        </div>

                    </div>

                </div>

            </div>

        </SellerLayout>

    );
}

export default SellerProfile;