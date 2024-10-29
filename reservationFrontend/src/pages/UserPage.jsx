import NavBar from "../components/NavBar"
import { useUserContext } from '../store/UserProvider';
import UserCard from "../components/UserCard"
import ReservationTable from "../features/ReservationTable";
const UserPage = () =>{

    const {user} = useUserContext();

    return (
        <div>
            <header>
                <NavBar/>
            </header>   
            <main className="flex items-end justify-center h-screen w-screen">
                <div className="grid grid-cols-2  w-4/5 h-4/5 mb-10 rounded divide-x divide-gray-200 bg-sky-50 p-2">
                    <div className="flex items-center justify-center">
                        <UserCard user={user}/>
                    </div>
                    <div className="flex items-center justify-center">
                        <ReservationTable/>
                    </div>
                </div>
            </main>
        </div>
    )
}

export default UserPage