import LoginForm from '../features/LoginForm';
import { GrRestaurant } from "react-icons/gr";
import { Link } from 'react-router-dom';

const LoginPage = () => {
  return (
    <main className="bg-gray-100 min-h-screen flex items-center justify-center">
      <div className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <GrRestaurant className="mx-auto h-20 w-auto" />
          <h1 className="text-center text-3xl font-bold"> Bienvenido a mesas 24/7</h1>
          <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
            Inicia sesion con tu cuenta
          </h2>
        </div>
        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <LoginForm/>
          <p className="mt-10 text-center text-sm text-gray-500">
            ¿No tienes una cuenta?
            <Link to="/signUp" className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500"> Registrate</Link>
          </p>
        </div>
      </div>
    </main>
  );
};

export default LoginPage;
