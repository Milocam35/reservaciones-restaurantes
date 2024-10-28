import { format, parse, add } from 'date-fns';

const HourForm = ({ restaurant, selectedHour, setSelectedHour }) => {
    const startHour = restaurant.openingHour;
    const endHour = restaurant.closingHour;

    // Convertir las horas de inicio y cierre en objetos Date
    const start = parse(startHour, 'HH:mm:ss', new Date());
    const end = parse(endHour, 'HH:mm:ss', new Date());

    // Crear un array de horas en el rango deseado
    const hoursInRange = [];
    let currentHour = start;

    while (currentHour <= end) {
        hoursInRange.push(currentHour);  // Almacena como objeto Date en lugar de formatear como string
        currentHour = add(currentHour, { hours: 1 });
    }

    return (
        <div className="form-container">
            <h1 className="font-semibold">Elige una hora:</h1>
            <section className="mt-4 mb-4 grid grid-cols-4 gap-2">
                {hoursInRange.map((hour, index) => (
                    <div
                        key={index}
                        onClick={() => setSelectedHour(hour)}  // Almacena el objeto Date
                        className={`text-sm text-center cursor-pointer border rounded
                        ${selectedHour?.getTime() === hour.getTime() ? 'bg-black text-white' : 'hover:bg-gray-200'}`}
                    >
                        {format(hour, 'HH:mm:ss')}  {/* Muestra hora en formato 'HH:mm:ss' */}
                    </div>
                ))}
            </section>
        </div>
    );
};

export default HourForm;
