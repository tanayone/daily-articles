import './NavLogSign.css'
import { Link } from 'react-router-dom';


export default function NavLogSign(){
    return(
        <div className="nav-login-signup-section">
            <Link to='/login'><button className="nav-login-btn">Log in</button></Link>
            <Link to='/register'><button className="nav-signup-btn">Sign up</button></Link>
        </div>
    );
}