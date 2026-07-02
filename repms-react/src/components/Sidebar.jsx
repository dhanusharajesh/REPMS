import {
    FiGrid,
    FiHome,
    FiFileText,
    FiTool,
    FiDollarSign,
    FiUser,
    FiLogOut
} from "react-icons/fi";
import {useNavigate, useLocation} from "react-router-dom";

function Sidebar() {
    const navigate = useNavigate();
    const location = useLocation();

    return (

        <aside className="sidebar">

            <div
                className="sidebar-logo"
                onClick={() => window.location.href="/seller"}>

                <img
                    src="/src/assets/logo.png"
                    alt="REPMS"
                />

                <span>REPMS</span>

            </div>

            <ul className="sidebar-menu">

                <li
                    className={
                        location.pathname === "/seller"
                            ? "active"
                            : ""
                    }
                    onClick={() => navigate("/seller")}
                >
                    <FiGrid />
                    Dashboard
                </li>

                <li
                    className={
                        location.pathname === "/seller/properties"
                            ? "active"
                            : ""
                    }
                    onClick={() => navigate("/seller/properties")}
                >
                    <FiHome />
                    Properties
                </li>

                <li
                    className={
                        location.pathname === "/seller/requests"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/seller/requests")
                    }
                >
                    <FiFileText />
                    Requests
                </li>

                <li
                    className={
                        location.pathname ===
                        "/seller/maintenance"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/seller/maintenance")
                    }
                >
                    <FiTool />
                    Maintenance
                </li>

                <li
                    className={
                        location.pathname ===
                        "/seller/payments"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/seller/payments")
                    }
                >
                    <FiDollarSign />
                    Payments
                </li>

                <li
                    className={
                        location.pathname ===
                        "/seller/profile"
                            ? "active"
                            : ""
                    }
                    onClick={() =>
                        navigate("/seller/profile")
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

export default Sidebar;