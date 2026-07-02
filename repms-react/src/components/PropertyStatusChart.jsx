import {
    Bar
} from "react-chartjs-2";

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Tooltip,
    Legend
} from "chart.js";

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Tooltip,
    Legend
);

function PropertyStatusChart() {

    const data = {

        labels: [
            "Available",
            "Rented",
            "Pending"
        ],

        datasets: [

            {
                data: [
                    12,
                    7,
                    3
                ],

                backgroundColor: [
                    "#570d06",
                    "#c97a63",
                    "#f3e3d8"
                ]
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

            <Bar
                data={data}
                options={options}
            />

        </div>
    );
}

export default PropertyStatusChart;