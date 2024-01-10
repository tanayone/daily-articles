import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import {useState, useEffect} from 'react';
import Registration from './pages/Registration';
import Home from './pages/Home';
import Login from './pages/Login';
import NotAvailable from './pages/NotAvailable';
import Upload from './pages/Upload';
import Post from './pages/Post';
import Profile from './pages/Profile';

function App() {
  const authData = localStorage.getItem('authData');
  const [isLoggedIn, setIsLoggedIn] = useState(authData !== null);

  useEffect(() => {
    setIsLoggedIn(authData !== null);
  }, [authData]);

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='*' element={<NotAvailable/>}/>
          <Route path='/' element={<Home/>}/>
          <Route path='/post/:pid' element={<Post/>}/>
          {!isLoggedIn && <Route path='/register' element={<Registration/>}/>}
          {!isLoggedIn && <Route path='/login' element={<Login/>}/>}
          {isLoggedIn && <Route path='/upload/' element={<Upload/>}/>}
          {isLoggedIn && <Route path='/profile/' element={<Profile/>}/>}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
