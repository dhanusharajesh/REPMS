const properties = [

    {
        name:"Green Villa",
        type:"Villa",
        address:"Chennai",
        rent:"15000",
        status:"Available",
        image:"images/villa1.jpg"
    },

    {
        name:"Lake View Apartment",
        type:"Apartment",
        address:"Chennai",
        rent:"12000",
        status:"Rented",
        image:"images/apartment1.jpg"
    },

    {
        name:"Palm House",
        type:"House",
        address:"Tambaram",
        rent:"18000",
        status:"Available",
        image:"images/house1.jpg"
    },

    {
        name:"Skyline Flat",
        type:"Flat",
        address:"Velachery",
        rent:"10000",
        status:"Rented",
        image:"images/flat1.jpg"
    }
];

const container =
    document.getElementById("propertyContainer");

if(container){

    properties.forEach(property => {

        let button = "";

        if(property.status === "Available"){
            button =
                `<button class="book-btn" onclick="window.location.href='booking.html?property=${encodeURIComponent(property.name)}'">
                    Book Now
                </button>`;
        }
        else{
            button =
                `<button class="booked-btn">Booked</button>`;
        }

        container.innerHTML += `
            <div class="property-card">

                <img src="${property.image}" alt="Property">

                <div class="property-info">

                    <h2>${property.name}</h2>

                    <p><strong>Type:</strong> ${property.type}</p>

                    <p><strong>Address:</strong> ${property.address}</p>

                    <p><strong>Rent:</strong> ₹${property.rent}/month</p>

                    <p class="status ${
            property.status === "Available"
                ? "available"
                : "rented"
        }">
                        ${property.status}
                    </p>

                    ${button}

                </div>

            </div>
        `;
    });

}

const params =
    new URLSearchParams(window.location.search);

const selectedPropertyName =
    params.get("property");

const propertySummary =
    document.getElementById("propertySummary");

if(propertySummary && selectedPropertyName){

    const selectedProperty =
        properties.find(
            p => p.name === selectedPropertyName
        );

    if(selectedProperty){

        propertySummary.innerHTML = `

            <div class="property-summary-card">

                <img src="${selectedProperty.image}"
                     alt="Property">

                <div class="property-summary-info">

                    <h3>${selectedProperty.name}</h3>

                    <p>
                        <strong>Type:</strong>
                        ${selectedProperty.type}
                    </p>

                    <p>
                        <strong>Address:</strong>
                        ${selectedProperty.address}
                    </p>

                    <p>
                        <strong>Rent:</strong>
                        ₹${selectedProperty.rent}/month
                    </p>

                </div>

            </div>

        `;
    }
}

/*const bookingForm =
    document.getElementById("bookingForm");

if(bookingForm){

    bookingForm.addEventListener("submit", function(e){

        e.preventDefault();

        window.location.href = "success.html";

    });

}*/