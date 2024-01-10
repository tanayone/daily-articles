import './Login.css';
import NavBar from '../components/NavBar';
import LoginForm from '../components/LoginForm';

export default function Login(){
    return(
        <div className='login-page'>
            <NavBar></NavBar>
            <div className='login-form-aligns'>
                <LoginForm></LoginForm>
            </div>
        </div>
    );
}