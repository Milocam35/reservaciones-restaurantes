import NavBar from  '../components/NavBar';
import RestaurantGrid  from '../features/RestaurantGrid';

const RestaurantPage = () =>{
    return (
        <div>
            <header>
                <NavBar/>
            </header>
            <main className='flex items-end justify-center h-screen w-screen'>
                <div className='flex flex-col items-center justify-end gap-5 w-screen h-full bg-white'>
                    <h1 className='font-semibold text-2xl'>Escoge Un Restaurante</h1>
                    <RestaurantGrid/>
                </div>
            </main>
        </div>
    )
}

export default RestaurantPage