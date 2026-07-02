import { useEffect, useState } from "react";
import BuyerLayout from "../layouts/BuyerLayout";

import {
    FiBookmark,
    FiFileText,
    FiCheckCircle,
    FiBell,
    FiSearch,
    FiCalendar,
    FiHeart,
    FiPhone
} from "react-icons/fi";

import "./BuyerDashboard.css";

function BuyerDashboard() {
    const [maintenanceNotices,
        setMaintenanceNotices] =
        useState([]);

    const [showNotifications,
        setShowNotifications] =
        useState(false);

    const [stats,
        setStats] =
        useState({});

    useEffect(() => {

        fetchMaintenanceNotices();

        fetchStats();

    }, []);

    const fetchMaintenanceNotices =
        async () => {

            const response =
                await fetch(
                    "http://localhost:8080/REPMS/buyer-maintenance",
                    {
                        credentials: "include"
                    }
                );

            const data =
                await response.json();

            setMaintenanceNotices(
                data
            );
        };
    const fetchStats =
        async () => {

            const response =
                await fetch(
                    "http://localhost:8080/REPMS/buyer-dashboard",
                    {
                        credentials: "include"
                    }
                );

            const data =
                await response.json();

            setStats(data);
        };

    return (

        <BuyerLayout>

            {/* Top Cards */}

            <div className="buyer-stats-grid">

                <div className="buyer-stat-card">

                    <FiBookmark />

                    <h3>
                        {stats.properties || 0}
                    </h3>

                    <p>
                        My Properties
                    </p>

                </div>

                <div className="buyer-stat-card">

                    <FiFileText />

                    <h3>
                        {stats.pendingRequests || 0}
                    </h3>

                    <p>
                        Pending Requests
                    </p>

                </div>

                <div className="buyer-stat-card">

                    <FiCheckCircle />

                    <h3>
                        {stats.approvedRequests || 0}
                    </h3>

                    <p>
                        Approved
                    </p>

                </div>

                <div
                    className="buyer-stat-card notification-card"
                    onClick={() =>
                        setShowNotifications(
                            !showNotifications
                        )
                    }
                >

                    <FiBell />

                    <h3>
                        {maintenanceNotices.length}
                    </h3>

                    <p>Notifications</p>

                </div>

            </div>

            {showNotifications && (

                <div className="maintenance-popup">

                    <h3>
                        Maintenance Notices
                    </h3>

                    {maintenanceNotices.length === 0 ? (

                        <p>
                            No notices available
                        </p>

                    ) : (

                        maintenanceNotices.map(
                            notice => (

                                <div
                                    key={notice.requestId}
                                    className="notice-item"
                                >

                                    <h4>
                                        {notice.propertyName}
                                    </h4>

                                    <p>
                                        {notice.issueDescription}
                                    </p>

                                    <span
                                        className={
                                            notice.status ===
                                            "COMPLETE"
                                                ? "complete"
                                                : "active"
                                        }
                                    >

                        {notice.status}

                    </span>

                                </div>

                            ))
                    )}

                </div>

            )}

            {/* Featured + Activity */}

            <div className="buyer-main-row">

                <div className="featured-properties">

                    <h3>Featured Properties For You</h3>

                    <div className="featured-grid">

                        <div className="featured-card">
                            <img
                                src="https://images.unsplash.com/photo-1568605114967-8130f3a36994"
                                alt=""
                            />

                            <h4>Meadow View</h4>
                            <p>Rs1250/month</p>
                        </div>

                        <div className="featured-card">
                            <img
                                src="https://images.unsplash.com/photo-1570129477492-45c003edd2be"
                                alt=""
                            />

                            <h4>The Stables</h4>
                            <p>Rs850/month</p>
                        </div>

                        <div className="featured-card">
                            <img
                                src="https://images.unsplash.com/photo-1512917774080-9991f1c4c750"
                                alt=""
                            />

                            <h4>Urban Condo</h4>
                            <p>Rs1000/month</p>
                        </div>

                    </div>

                </div>

                <div className="activity-panel">

                    <h3>Recent Activity</h3>

                    <ul>

                        <li>Viewed Green Meadows</li>

                        <li>Saved Lake View Apartment</li>

                        <li>Requested Sky Residency</li>

                        <li>Viewed Palm Residency</li>

                    </ul>

                </div>

            </div>

            {/* Tools + Map */}

            <div className="buyer-tools-row">

                <div className="buyer-tools">

                    <h3>Buyer Tools</h3>

                    <div className="tools-grid">

                        <button>
                            <FiSearch />
                            Search
                        </button>

                        <button>
                            <FiCalendar />
                            Schedule Visit
                        </button>

                        <button>
                            <FiHeart />
                            Saved
                        </button>

                        <button>
                            <FiPhone />
                            Contact
                        </button>

                    </div>

                </div>

                <div className="buyer-map">

                    <h3>Nearby Properties</h3>

                    <div className="map-placeholder">

                        <img
                            src="/images/buyers-map.jpg"
                            alt="Nearby Properties"
                            className="buyer-map-image"
                        />

                    </div>

                </div>

            </div>

        </BuyerLayout>

    );
}

export default BuyerDashboard;