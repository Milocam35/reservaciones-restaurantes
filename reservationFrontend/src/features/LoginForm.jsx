import EmailInput from "../components/auth/EmailInput";
import PasswordInput from "../components/auth/PasswordInput";
import SubmitButton from "../components/auth/SubmitButton";
import { useNavigate } from 'react-router-dom';
import { UserContext } from "../store/UserProvider";
import axios from 'axios';

import { useState, useContext } from "react";

const LoginForm  = () => {

  const  [email, setEmail] = useState("");
  const  [password, setPassword] = useState("");
  const { setUser } = useContext(UserContext);
  const navigate = useNavigate();

  async function login(event) {
    event.preventDefault();
    try {
      // Primero, realiza el login
      await axios.post("http://localhost:4000/api/user/login", {
        email: email,
        password: password,
      }).then(async (res) => {
        console.log(res.data);

        if (res.data.message === "Email not exits") {
          alert("Email does not exist");
        } else if (res.data.message === "Login Success") {
          alert("Login successful");

          // Si el login es exitoso, realiza una segunda solicitud para obtener los datos del usuario
          const userResponse = await axios.get(`http://localhost:4000/api/user/email/${email}`);
          
          // Guarda los datos del usuario en el contexto
          setUser(userResponse.data);
          console.log(userResponse.data)

          // Redirige al usuario a la página de destino
          navigate('/Restaurants');
        } else {
          alert("Incorrect Email and Password not match");
        }
      }, fail => {
        console.error(fail);
      });
    } catch (err) {
      alert("An error occurred: " + err);
    }
  }
  

  return (
        <form className="space-y-6">
          <EmailInput value={email} onChange={(e) => setEmail(e.target.value)}/>
          <PasswordInput value={password} onChange={(e) => setPassword(e.target.value)}/>
          <SubmitButton buttonText="Submit" onClick={login}/>
        </form>
  );
};

export default LoginForm;
