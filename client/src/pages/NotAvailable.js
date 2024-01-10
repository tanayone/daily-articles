import NavBar from '../components/NavBar';
import './NotAvailable.css';

export default function NotAvailable(){
    return(
        <div className='not-available-page'>
            <NavBar></NavBar>
            <h1>This page isn't avaiable!</h1>
        </div>
    );
}