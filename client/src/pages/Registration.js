import './Registration.css';
import NavBar from '../components/NavBar';
import RegistrationForm from '../components/RegistrationForm';

export default function Registration(){
    return(
        <div className='registration-page'>
            <NavBar></NavBar>
            <div className='reg-form-aligns'>
                <RegistrationForm></RegistrationForm>
            </div>
        </div>
    );
}