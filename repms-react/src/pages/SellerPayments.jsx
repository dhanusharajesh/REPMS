import SellerLayout from "../layouts/SellerLayout";
import "./SellerPayments.css";

function SellerPayments() {

    const payments = [

        {
            property: "Sunrise Villa",
            tenant: "John Doe",
            amount: 45000,
            month: "June 2026",
            status: "Paid"
        },

        {
            property: "Palm Residency",
            tenant: "Rhea Raj",
            amount: 30000,
            month: "June 2026",
            status: "Paid"
        },

        {
            property: "Skyline Towers",
            tenant: "John Doe",
            amount: 20000,
            month: "June 2026",
            status: "Pending"
        }

    ];

    return (

        <SellerLayout>

            <div className="seller-payments-page">

                <h1>
                    Payments
                </h1>

                {/* Summary Cards */}

                <div className="payment-stats">

                    <div className="payment-stat-card">

                        <h2>
                            ₹95,000
                        </h2>

                        <p>
                            Received
                        </p>

                    </div>

                    <div className="payment-stat-card">

                        <h2>
                            ₹20,000
                        </h2>

                        <p>
                            Pending
                        </p>

                    </div>

                    <div className="payment-stat-card">

                        <h2>
                            ₹1,15,000
                        </h2>

                        <p>
                            Monthly Revenue
                        </p>

                    </div>

                </div>

                {/* Table */}

                <div className="payment-table">

                    <table>

                        <thead>

                        <tr>

                            <th>Property</th>
                            <th>Tenant</th>
                            <th>Rent</th>
                            <th>Month</th>
                            <th>Status</th>

                        </tr>

                        </thead>

                        <tbody>

                        {payments.map((payment,index) => (

                            <tr key={index}>

                                <td>
                                    {payment.property}
                                </td>

                                <td>
                                    {payment.tenant}
                                </td>

                                <td>
                                    ₹{payment.amount}
                                </td>

                                <td>
                                    {payment.month}
                                </td>

                                <td>

                                    <span
                                        className={`payment-status ${payment.status.toLowerCase()}`}
                                    >

                                        {payment.status}

                                    </span>

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

                {/* Bottom Section */}

                <div className="payments-bottom">

                    <div className="qr-card">

                        <h3>
                            Receive Payments
                        </h3>

                        <img
                            src="/images/qr.jpg"
                            alt="QR"
                        />

                        <p>
                            seller@upi
                        </p>

                    </div>

                    <div className="transactions-card">

                        <h3>
                            Recent Transactions
                        </h3>

                        <ul>

                            <li>
                                ₹45,000 from John Doe
                            </li>

                            <li>
                                ₹30,000 from Rhea Raj
                            </li>

                            <li>
                                ₹20,000 pending from John Doe
                            </li>

                        </ul>

                    </div>

                </div>

            </div>

        </SellerLayout>

    );
}

export default SellerPayments;