import { useUserContext } from '../store/UserProvider';
import { useState, useEffect, useCallback } from 'react';
import axios from 'axios';

const ReservationTable = () => {
    const { user } = useUserContext();
    const [reservations, setReservations] = useState([]); 

    const fetchReservationDetails = async (id) => {
        try {
            const response = await axios.get(`http://localhost:4000/api/reservation/${id}`);
            return response.data;
        } catch (error) {
            console.error('Error al obtener la reservación:', error);
            return null;
        }
    };

    const reloadReservations = useCallback(async () => {
        if (user?.reservationList) {
            const reservationIds = user.reservationList.map(res => res.reservationId);
            const detailedReservations = await Promise.all(
                reservationIds.map(id => fetchReservationDetails(id))
            );
            setReservations(detailedReservations.filter(res => res !== null));
        }
    }, [user?.reservationList]);

    useEffect(() => {
        reloadReservations();
    }, [reloadReservations]);

    return (
        <section className='flex justify-center items-center flex-col w-5/6 h-5/6 rounded gap-5'>
            <div>
                <h1 className="font-bold">Reservaciones:</h1>
            </div>
            <div className='flex flex-col w-full overflow-y-auto max-h-[400px] rounded'>
                <table className="w-full">
                    <thead className='bg-gray-50 border-b-2 border-gray-200'>
                        <tr>
                            <th className="p-3 text-sm font-semibold tracking-wide text-left">Fecha</th>
                            <th className="p-3 text-sm font-semibold tracking-wide text-left">Hora</th>
                            <th className="p-3 text-sm font-semibold tracking-wide text-left">Estado</th>
                            <th className="p-3 text-sm font-semibold tracking-wide text-left">Mesa</th>
                            <th className="p-3 text-sm font-semibold tracking-wide text-left">Restaurante</th>
                        </tr>
                    </thead>
                    <tbody>
                    {reservations.map((reservation, index) => (
                            <tr 
                                className={`${index % 2 === 0 ? 'bg-white' : 'bg-gray-100'}`} 
                                key={reservation.reservationId}
                            >
                                <td className="p-3 text-sm text-gray-700">{reservation.reservationDate}</td>
                                <td className="p-3 text-sm text-gray-700">{reservation.reservationHour}</td>
                                <td className="p-3 text-sm text-gray-700">{reservation.status}</td>
                                <td className="p-3 text-sm text-gray-700">
                                    {reservation.table?.tableNumber || 'N/A'}
                                </td>
                                <td className="p-3 text-sm text-gray-700">
                                    {reservation.restaurant?.name || 'N/A'}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </section>
    );
};

export default ReservationTable;
