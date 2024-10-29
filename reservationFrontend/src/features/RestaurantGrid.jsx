import { useEffect, useState } from "react";
import axios from 'axios';
import { useUserContext } from '../store/UserProvider';
import RestaurantCard from "../components/RestaurantCard";
import { useNavigate } from 'react-router-dom';

const RestaurantGrid = () => {
    const [restaurants, setRestaurants] = useState([]);
    const { setRestaurant } = useUserContext();
    const navigate = useNavigate();

    const selectRestaurant = (selectedRestaurant) => {
        setRestaurant(selectedRestaurant);
        navigate('/Restaurants/Reservations');
    };

    useEffect(() => {
        // Función para obtener la lista de restaurantes
        const fetchRestaurants = async () => {
            try {
                const response = await axios.get("http://localhost:4000/api/restaurant");
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
                    onClick={() => selectRestaurant(restaurant)}
                />
            ))}
        </section>
    );
};

export default RestaurantGrid;


