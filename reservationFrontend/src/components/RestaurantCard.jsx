const RestaurantCard = ({imageSrc, name, address, phone, openingHour, closingHour}) => {
    return (
        <div className="flex flex-col bg-white shadow-md rounded overflow-hidden">
            <img
                src={imageSrc}
                alt="Restaurant Image"
                className="w-full h-1/2 object-cover"
            />
            <div className="p-4 text-center">
                <h2 className="text-lg font-bold m-3">{name}</h2>
                <p className="text-xs text-gray-600 m-2">Direccion: {address}</p>
                <p className="text-xs text-gray-600 m-2">Telefono: {phone}</p>
                <p className="text-xs text-gray-600 m-2">Hora de apertura: {openingHour}</p>
                <p className="text-xs text-gray-600 m-2">Hora de cierre: {closingHour}</p>
            </div>
        </div>
    );
}

export default RestaurantCard