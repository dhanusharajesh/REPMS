function RecentActivity() {

    const activities = [

        "New property added",
        "Lease approved",
        "Maintenance request received",
        "Payment received"

    ];

    return (

        <div className="activity-list">

            {
                activities.map((item,index)=>(

                    <div
                        key={index}
                        className="activity-item"
                    >
                        {item}
                    </div>

                ))
            }

        </div>

    );
}

export default RecentActivity;