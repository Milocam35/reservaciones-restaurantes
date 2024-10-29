import { FaUserCircle } from "react-icons/fa";
const UserCard = ({user}) =>{
    return (
        <section className="flex justify-center items-center flex-col bg-white w-5/6 h-5/6 rounded shadow gap-5">
            <div className="flex flex-col justify-center items-center">
                <FaUserCircle size="80%"/>
                <h2 className="text-2xl font-semibold">{user.name}</h2>
            </div>
            <div className="flex flex-col gap-4 mb-19">
                <h2><strong>Celular:</strong> {user.cellphone}</h2>
                <h2><strong>Correo:</strong> {user.email}</h2>
                <h2><strong>Rol:</strong> {user.role}</h2>
            </div>
        </section>
    )
}

export default UserCard