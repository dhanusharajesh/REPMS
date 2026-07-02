import SellerLayout from "../layouts/SellerLayout";
import StatCard from "../components/StatCard";
import RevenueChart from "../components/RevenueChart";
import PropertyStatusChart from "../components/PropertyStatusChart";
import SellerTools from "../components/SellerTools";
import RecentActivity from "../components/RecentActivity";
import MapView from "../components/MapView";
import {
    FiHome,
    FiCheckCircle,
    FiUsers,
    FiFileText
} from "react-icons/fi";
import { useEffect, useState } from "react";

function SellerDashboard() {
    const [stats, setStats] =
        useState({});

    useEffect(() => {

        fetchStats();

    }, []);
    const fetchStats = async () => {

        const response =
            await fetch(
                "http://localhost:8080/REPMS/seller-dashboard",
                {
                    credentials: "include"
                }
            );

        const data =
            await response.json();

        setStats(data);
    };


    return (

        <SellerLayout>

            <div className="stats-grid">

                <StatCard
                    title="Total Properties"
                    value={stats.totalProperties}
                    icon={<FiHome />}
                />

                <StatCard
                    title="Available"
                    value={stats.available}
                    icon={<FiCheckCircle />}
                />

                <StatCard
                    title="Rented"
                    value={stats.rented}
                    icon={<FiUsers />}
                />

                <StatCard
                    title="Requests"
                    value={stats.requests}
                    icon={<FiFileText />}
                />

            </div>

            <div className="chart-row">

                <div className="dashboard-card">

                    <>
                        <h3>Performance Overview</h3>

                        <RevenueChart />
                    </>

                </div>

                <div className="dashboard-card">

                    <>
                        <h3>Property Status</h3>

                        <PropertyStatusChart />
                    </>

                </div>

            </div>

            <div className="bottom-row">

                <div className="dashboard-card">

                    <>
                        <h3>Seller Tools</h3>
                        <SellerTools />
                    </>

                </div>

                <div className="dashboard-card">

                    <>
                        <h3>Recent Activity</h3>
                        <RecentActivity />
                    </>

                </div>

                <div className="dashboard-card">

                    <>
                        <h3>Map View</h3>
                        <MapView />
                    </>

                </div>

            </div>

        </SellerLayout>

    );
}

export default SellerDashboard;