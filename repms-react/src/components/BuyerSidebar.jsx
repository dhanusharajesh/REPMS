import {
    FiGrid,
    FiHome,
    FiFileText,
    FiBriefcase,
    FiDollarSign,
    FiUser,
    FiLogOut
} from "react-icons/fi";

import { useNavigate, useLocation } from "react-router-dom";

function BuyerSidebar() {

    const navigate = useNavigate();
    const location = useLocation();

    const user =
        JSON.parse(
            localStorage.getItem("user")
        );

    return (

        <aside className="sidebar buyer-sidebar">

            <div
                className="sidebar-logo"
                onClick={() => navigate("/buyer")}
            >

                <img
                    src="/src/assets/logo.png"
                    alt="REPMS"
                />

                <span>REPMS</span>

            </div>

            <ul className="sidebar-menu">

                <li
                    className={
                        location.pathname === "/buyer"
                            ? "active"
                            : ""
                    }
                    onClick={() => navigate("/buyer")}
                >
                    <FiGrid />
                    Dashboard
                </li>

                <li
                    className={
                        location.pathname === "/buyer/properties"
                            ? "active"
                            : ""
                    }
                    onClick={() => navigate("/buyer/properties")}
                >
                    <FiHome />
                    Properties
                </li>

                <li
                    className={
                        location.pathname === "/buyer/requests"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/buyer/requests")
                    }
                >
                    <FiFileText />
                    My Requests
                </li>

                <li
                    className={
                        location.pathname === "/buyer/my-properties"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/buyer/my-properties")
                    }
                >
                    <FiBriefcase />
                    My Properties
                </li>

                <li
                    className={
                        location.pathname ===
                        "/buyer/payments"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/buyer/payments")
                    }
                >
                    <FiDollarSign />
                    Payments
                </li>

                <li
                    className={
                        location.pathname ===
                        "/buyer/profile"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/buyer/profile")
                    }
                >
                    <FiUser />
                    Profile
                </li>

            </ul>

            <div
                className="logout-item"
                onClick={() => {

                    localStorage.removeItem("user");

                    window.location.href = "/";

                }}
            >

                <FiLogOut />
                Logout

            </div>

        </aside>

    );
}

export default BuyerSidebar;