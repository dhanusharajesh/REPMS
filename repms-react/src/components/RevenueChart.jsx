import {
    Line
} from "react-chartjs-2";

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Filler,
    Tooltip,
    Legend
} from "chart.js";

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Filler,
    Tooltip,
    Legend
);

function RevenueChart() {

    const data = {

        labels: [
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun"
        ],

        datasets: [

            {
                label: "Property Revenue",

                data: [
                    20,
                    40,
                    35,
                    65,
                    55,
                    80
                ],

                borderColor: "#570d06",

                backgroundColor:
                    "rgba(87,13,6,0.15)",

                fill: true,

                tension: 0.4
            }

        ]
    };

    const options = {

        responsive: true,

        maintainAspectRatio: false,

        plugins: {

            legend: {

                display: false
            }
        }
    };

    return (

        <div
            style={{
                height: "100%"
            }}
        >

            <Line
                data={data}
                options={options}
            />

        </div>
    );
}

export default RevenueChart;