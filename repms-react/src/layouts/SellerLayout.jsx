import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";

function SellerLayout({ children }) {

    return (

        <div className="dashboard-container">

            <Sidebar />

            <div className="main-section">

                <Navbar />

                <main className="content-area">

                    {children}

                </main>

            </div>

        </div>

    );
}

export default SellerLayout;