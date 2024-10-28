import NavBar from "../components/NavBar"
import ReservationForm from "../features/ReservationForm";
import { useUserContext } from '../store/UserProvider';

const ReservationPage = () => {

    const { restaurant} = useUserContext();

    return(
        <div>
            <header>
                <NavBar/>
            </header>
            <main className="flex items-center justify-center h-screen w-screen">
                <div className='flex flex-col items-center justify-center gap-5 w-screen h-full bg-white'>
                    <h1 className="text-2xl font-semibold">Haz tu reservacion en: {restaurant.name}</h1>
                    <ReservationForm/>
                </div>
            </main>
        </div>
        )
}

export default ReservationPage