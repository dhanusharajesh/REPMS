function StatCard({
                      title,
                      value,
                      icon
                  }) {

    return (

        <div className="stat-card">

            <div className="stat-header">

                <h4>
                    {title}
                </h4>

                <div className="stat-icon">
                    {icon}
                </div>

            </div>

            <h2>
                {value}
            </h2>

            <p className="stat-growth">
                +12% this month
            </p>

        </div>

    );
}

export default StatCard;