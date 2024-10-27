import EmailInput from "../components/auth/EmailInput";
import PasswordInput from "../components/auth/PasswordInput";
import SubmitButton from "../components/auth/SubmitButton";
const LoginForm  = () => {
  return (
        <form className="space-y-6" action="#" method="POST">
          <EmailInput />
          <PasswordInput/>
          <SubmitButton/>
        </form>
  );
};

export default LoginForm;
