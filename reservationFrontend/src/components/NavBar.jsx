import { Link, useLocation } from 'react-router-dom';
import { FcPlanner } from "react-icons/fc";
import { LuUserCircle2 } from "react-icons/lu";
import React, {useContext } from 'react';
import { UserContext } from '../store/UserProvider';

const NavBar = () =>{

    const { user } = useContext(UserContext);
    const location = useLocation();

    // Genera un array con los segmentos de la URL
    const pathSegments = location.pathname.split('/').filter(Boolean);

    // Crea enlaces de navegación para cada segmento de la URL
    const breadcrumbs = pathSegments.map((segment, index) => {
        const path = `/${pathSegments.slice(0, index + 1).join('/')}`;
        const segmentName = segment === "Restaurants" ? "Home" : segment;

        return (
            <React.Fragment key={path}>
                {index > 0 && " / "}
                <Link to={path} className="breadcrumb-link">{segmentName}</Link>
            </React.Fragment>
        );
    });

    return (
        <nav className='flex justify-around items-center bg-white border border-gray-300 w-screen mx-auto fixed'>
            <div className='flex items-center'>
                <FcPlanner size="50px" className='m-2'/>
                <h2 className='font-bold'>Mesas 24/7</h2>
            </div>
            <div>
                {breadcrumbs}
            </div>
            <div className='flex items-center'>
                <Link to="/Restaurants/User" className="flex items-center">
                    <h2 className='m-2'>Hola {user.name}</h2>
                </Link>
                <LuUserCircle2 size="30px" />
            </div>
        </nav>
    )
}

export default NavBar