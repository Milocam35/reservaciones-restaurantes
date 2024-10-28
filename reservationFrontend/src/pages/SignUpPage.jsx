import RegistrationForm from "../features/RegistrationForm"
const SignUpPage = () =>{
    return(
        <div className="bg-gray-100 h-screen flex items-center justify-center">
            <div className="flex min-h-full w-screen flex-col justify-center px-6 py-12 lg:px-8">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <h1 className="text-center text-3xl font-bold"> Registrate</h1>
                </div>
                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <RegistrationForm/>
                </div>
            </div>
        </div>
        
    )
}

export default SignUpPage
