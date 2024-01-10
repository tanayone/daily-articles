import { useState, useEffect } from 'react';
import './NavBar.css';
import NavLogSign from './NavLogSign';
import NavUserInfo from './NavUserInfo';
import { Link } from 'react-router-dom';

export default function NavBar() {
  const authData = localStorage.getItem('authData');
  const [isLoggedIn, setIsLoggedIn] = useState(authData !== null);

  useEffect(() => {
    setIsLoggedIn(authData !== null);
  }, [authData]);

  return (
    <div className="navbar-container">
      <div className="navbar-container-left">
        <Link to="/"><h3>DAILY<span>ARTICLES</span></h3></Link>
      </div>
      <div className="navbar-container-right">
        {isLoggedIn ? <NavUserInfo/> : <NavLogSign/>}
      </div>
    </div>
  );
}