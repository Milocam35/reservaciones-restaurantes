import EmailInput from "../components/auth/EmailInput";
import NameInput from "../components/auth/NameInput";
import CellPhoneInput from "../components/auth/CellPhoneInput";
import PasswordInput from "../components/auth/PasswordInput";
import { Link } from 'react-router-dom';
import axios from 'axios';


import { useState } from "react";

const RegistrationForm = () =>{

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [cellPhone, setCellPhone] = useState("");
  const [password, setPassword] = useState("");

  async function save(event) {
    event.preventDefault();
    try {
      await axios.post("http://localhost:4000/api/user/save", {
      name: name,
      cellphone: cellPhone,
      email: email,
      password: password,
      role: "client"
      });
      alert("User Registration Successfully");
    } catch (err) {
      alert("User Registration Failed, try filling all the text boxes");
    }
  }


    return(
        <form className="space-y-6">
            <NameInput value={name} onChange={(e) => setName(e.target.value)}/>
            <EmailInput value={email} onChange={(e) => setEmail(e.target.value)}/>
            <CellPhoneInput value={cellPhone} onChange={(e) => setCellPhone(e.target.value)}/>
            <PasswordInput  value={password} onChange={(e) => setPassword(e.target.value)}/>

            <div className="mt-6 flex items-center justify-end gap-x-6">
              <Link to="/"><button type="button" className="text-sm font-semibold leading-6 text-gray-900">Cancel</button></Link>
              <button type="submit" className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" onClick={save}>Save</button>
            </div>
        </form>
      )
}

export default RegistrationForm;