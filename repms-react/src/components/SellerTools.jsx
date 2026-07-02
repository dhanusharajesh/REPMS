import {
    FiPlus,
    FiHome,
    FiFileText,
    FiCalendar
} from "react-icons/fi";

function SellerTools() {

    return (

        <div className="tools-grid">

            <button className="tool-btn">

                <FiPlus />

                Add Property

            </button>

            <button className="tool-btn">

                <FiHome />

                Manage Properties

            </button>

            <button className="tool-btn">

                <FiFileText />

                Requests

            </button>

            <button className="tool-btn">

                <FiCalendar />

                Reports

            </button>

        </div>

    );
}

export default SellerTools;