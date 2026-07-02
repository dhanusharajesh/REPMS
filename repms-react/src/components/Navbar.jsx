import { FiSearch, FiBell } from "react-icons/fi";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Navbar() {
    const navigate = useNavigate();

    const [searchText,
        setSearchText] =
        useState("");

    const user =
        JSON.parse(
            localStorage.getItem("user")
        );

    return (

        <div className="top-navbar">

            <div className="search-box">

                <FiSearch />

                <input
                    type="text"
                    placeholder="Search properties..."
                    value={searchText}
                    onChange={(e) =>
                        setSearchText(
                            e.target.value
                        )
                    }
                    onKeyDown={(e) => {

                        if(e.key === "Enter"){

                            const user =
                                JSON.parse(
                                    localStorage.getItem(
                                        "user"
                                    )
                                );

                            if(user.role === "ADMIN"){

                                navigate(
                                    `/seller/properties?search=${searchText}`
                                );

                            }else{

                                navigate(
                                    `/buyer/properties?search=${searchText}`
                                );
                            }
                        }
                    }}
                />

            </div>

            <div className="navbar-right">

                <button className="notification-btn">

                    <FiBell />

                </button>

                <div className="user-profile">

                    <img
                        src={`https://ui-avatars.com/api/?name=${user?.username}`}
                        alt="Profile"
                    />

                    <span>

                        {user?.username}

                    </span>

                </div>

            </div>

        </div>

    );
}

export default Navbar;