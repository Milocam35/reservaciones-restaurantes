import { Link } from 'react-router-dom';
import { useEffect, useState } from "react";
import axios from 'axios';
import RestaurantCard from "../components/RestaurantCard";

const RestaurantGrid = () => {
    const [restaurants, setRestaurants] = useState([]);

    // URL del backend donde se obtiene la lista de restaurantes
    const apiEndpoint = "http://localhost:4000/api/restaurant";

    useEffect(() => {
        // Función para obtener la lista de restaurantes
        const fetchRestaurants = async () => {
            try {
                const response = await axios.get(apiEndpoint);
                setRestaurants(response.data);
            } catch (error) {
                console.error("Error fetching restaurants:", error);
            }
        };

        fetchRestaurants();
    }, []);

    return (
        <section className="w-5/6 h-5/6 bg-sky-50 grid grid-cols-3 gap-4 mx-auto p-4 rounded">
            {restaurants.map((restaurant, index) => (
                <RestaurantCard 
                    key={index}
                    imageSrc="https://dynamic-media-cdn.tripadvisor.com/media/photo-o/21/21/5f/e3/piazza-italia-by-storia.jpg"
                    name={restaurant.name}
                    address={restaurant.address}
                    phone={restaurant.phone}
                    openingHour={restaurant.openingHour}
                    closingHour={restaurant.closingHour}
                />
            ))}
        </section>
    );
};

export default RestaurantGrid;


