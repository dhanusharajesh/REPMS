import { useState } from "react";
import { useNavigate } from "react-router-dom";

import "./Login.css";
import loginImage from "../assets/login-bg.png";

function Login() {
    const [role, setRole] = useState("buyer");

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();
    const handleLogin = async () => {

        try {

            const formData =
                new URLSearchParams();

            formData.append(
                "username",
                username
            );

            formData.append(
                "password",
                password
            );

            const response =
                await fetch(
                    "http://localhost:8080/REPMS/login",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/x-www-form-urlencoded"
                        },

                        body: formData,

                        credentials: "include"
                    }
                );

            const data = await response.json();

            console.log(data);
            localStorage.setItem(
                "user",
                JSON.stringify(data)
            );

            if(data.success){

                localStorage.setItem(
                    "userId",
                    data.userId
                );

                localStorage.setItem(
                    "username",
                    data.username
                );

                localStorage.setItem(
                    "role",
                    data.role
                );

                if(data.role === "ADMIN"){
                    navigate("/seller");
                }
                else{
                    navigate("/buyer");
                }
            }
            else{
                alert("Invalid username or password");
            }

        }
        catch(error){

            console.error(error);
        }
    };
    return (
        <div
            className="login-page"
            style={{
                backgroundImage: `url(${loginImage})`
            }}
        >

            <div className="login-container">

                <div className="login-left">

                    <img
                        src="/src/assets/hero.png"
                        alt="Hero"
                        className="hero-image"
                    />

                </div>

                <div className="login-right">

                    <div className="login-form">

                        <div className="login-logo">

                            <img
                                src="/src/assets/logo.png"
                                alt="REPMS Logo"
                            /> <h2>REPMS</h2>

                        </div>

                        <p>
                            Real Estate Property Management System
                        </p>

                        <div className="role-selection">

                            <button
                                className={`role-btn ${
                                    role === "buyer"
                                        ? "active-role"
                                        : ""
                                }`}
                                onClick={() => setRole("buyer")}
                            >
                                Buyer
                            </button>

                            <button
                                className={`role-btn ${
                                    role === "seller"
                                        ? "active-role"
                                        : ""
                                }`}
                                onClick={() => setRole("seller")}
                            >
                                Seller
                            </button>

                        </div>

                        <input
                            type="text"
                            placeholder="Username"
                            value={username}
                            onChange={(e) =>
                                setUsername(e.target.value)
                            }
                        />

                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) =>
                                setPassword(e.target.value)
                            }
                        />

                        <div className="forgot-password">

                            <a href="#">
                                Forgot Password?
                            </a>

                        </div>

                        <button
                            className="login-btn"
                            onClick={handleLogin}
                        >
                            Login
                        </button>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Login;