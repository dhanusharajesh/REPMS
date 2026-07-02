import BuyerSidebar from "../components/BuyerSidebar";
import Navbar from "../components/Navbar";

function BuyerLayout({ children }) {

    return (

        <div className="dashboard-container">

            <BuyerSidebar />

            <div className="main-section">

                <Navbar />

                <main className="content-area">

                    {children}

                </main>

            </div>

        </div>

    );
}

export default BuyerLayout;