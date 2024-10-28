import { useState } from 'react';
import axios from 'axios'; // Asegúrate de importar axios
import DateForm from "../components/DateForm";
import TableForm from "../components/TableForm";
import HourForm from "../components/HourForm";
import { format, parse } from 'date-fns';
import { useUserContext } from '../store/UserProvider';
import SubmitButton from '../components/auth/SubmitButton';

const ReservationForm = () => {
    const { user, restaurant } = useUserContext();

    const startHour = restaurant ? parse(restaurant.openingHour, 'HH:mm:ss', new Date()) : new Date();
    
    const [selectedDay, setSelectedDay] = useState(new Date());
    const [selectedHour, setSelectedHour] = useState(startHour);
    const [selectedTable, setSelectedTable] = useState(null);

    async function save(event) {
        event.preventDefault();
        try {
          await axios.post("http://localhost:4000/api/reservation/save", {
              reservationDate: format(selectedDay, 'yyyy-MM-dd'),
              reservationHour: format(selectedHour, 'HH:mm:ss'),
              status: "confirmed",
              user: { userId: user.userId },
              table: { tableId: selectedTable?.tableId },
              restaurant: { restaurantId: restaurant.restaurantId }
          });
          alert("Reservation Created Successfully");
        } catch (err) {
          alert("Error creating reservation, please try again.");
        }
    }

    return (
        <div>
            <TableForm restaurant={restaurant} selectedTable={selectedTable} setSelectedTable={setSelectedTable} />
            <div className="pt-10">
                <div className="max-w-md px-4 mx-auto sm:px-7 md:max-w-4xl md:px-6">
                    <div className="md:grid md:grid-cols-2 md:divide-x md:divide-gray-200">
                        <DateForm selectedDay={selectedDay} setSelectedDay={setSelectedDay} />
                        <section className="mt-12 md:mt-0 md:pl-14">
                            <HourForm restaurant={restaurant} selectedHour={selectedHour} setSelectedHour={setSelectedHour} />

                            <h2 className="font-semibold text-gray-900">
                                Reserva para{' '}
                                <time dateTime={`${format(selectedDay, 'yyyy-MM-dd')}T${format(selectedHour, 'HH:mm:ss')}`}>
                                    {`${format(selectedDay, 'MMM dd, yyyy')} a las ${format(selectedHour, 'HH:mm:ss')}`}
                                </time>
                            </h2>
                        </section>
                    </div>
                </div>
            </div>
            <div className='mt-10 w-full'>
                <SubmitButton buttonText="Reservar" onClick={save}/>
            </div>
        </div>
    );
};

export default ReservationForm;
