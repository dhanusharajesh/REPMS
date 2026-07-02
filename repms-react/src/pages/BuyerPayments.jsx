import BuyerLayout from "../layouts/BuyerLayout";
import "./BuyerPayments.css";

function BuyerPayments() {

    const payments = [

        {
            property: "Sunrise Villa",
            month: "May 2026",
            amount: 45000,
            status: "Paid"
        },

        {
            property: "Sunrise Villa",
            month: "April 2026",
            amount: 45000,
            status: "Paid"
        },

        {
            property: "Sunrise Villa",
            month: "March 2026",
            amount: 45000,
            status: "Paid"
        }

    ];

    return (

        <BuyerLayout>

            <div className="buyer-payments-page">

                <h1>
                    Payments
                </h1>

                {/* Summary */}

                <div className="buyer-payment-stats">

                    <div className="buyer-payment-card">

                        <h2>
                            ₹45,000
                        </h2>

                        <p>
                            Current Rent
                        </p>

                    </div>

                    <div className="buyer-payment-card">

                        <h2>
                            ₹5,000
                        </h2>

                        <p>
                            Pending Dues
                        </p>

                    </div>

                    <div className="buyer-payment-card">

                        <h2>
                            18 Days
                        </h2>

                        <p>
                            Lease Remaining
                        </p>

                    </div>

                </div>

                {/* Current Payment */}

                <div className="current-payment-card">

                    <h3>
                        Current Payment
                    </h3>

                    <div className="payment-details">

                        <div className="payment-detail">
                            <label>Property</label>
                            <span>Sunrise Villa</span>
                        </div>

                        <div className="payment-detail">
                            <label>Monthly Rent</label>
                            <span>₹45,000</span>
                        </div>

                        <div className="payment-detail">
                            <label>Due Date</label>
                            <span>15-Jun-2026</span>
                        </div>

                        <div className="payment-detail">
                            <label>Status</label>
                            <span>Pending</span>
                        </div>

                    </div>

                    <button className="pay-now-btn">

                        Pay Now

                    </button>

                </div>

                {/* Payment History */}

                <div className="payment-history">

                    <h3>
                        Payment History
                    </h3>

                    <table>

                        <thead>

                        <tr>

                            <th>Property</th>
                            <th>Month</th>
                            <th>Amount</th>
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
                                    {payment.month}
                                </td>

                                <td>
                                    ₹{payment.amount}
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

                {/* Charges */}

                <div className="upcoming-charges">

                    <h3>
                        Additional Charges
                    </h3>

                    <div className="charge-item">
                        <span className="charge-name">Maintenance Fee</span>
                        <span className="charge-amount">₹500</span>
                    </div>

                    <div className="charge-item">
                        <span className="charge-name">Parking Fee</span>
                        <span className="charge-amount">₹1,000</span>
                    </div>

                    <div className="charge-item">
                        <span className="charge-name">Water Charges</span>
                        <span className="charge-amount">₹300</span>
                    </div>

                </div>

            </div>

        </BuyerLayout>

    );
}

export default BuyerPayments;