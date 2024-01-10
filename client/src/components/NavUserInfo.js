import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './NavUserInfo.css';
import home from './img/home.png';
import profile from './img/profile.png';
import upload from './img/upload.png';
import { getUser } from '../private/Secured';

export default function NavUserInfo() {
    const [name, setName] = useState();
    const authData = getUser();
    const navigateTo = useNavigate();

    useEffect(() => {
        async function fetchData() {
            if (authData !== null) {
                try {
                    const url = "http://localhost:8080/name";
                    const response = await fetch(url, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json; charset=utf8',
                        },
                        body: authData,
                    });

                    if (!response.ok) {
                        throw new Error(`${response.status}`);
                    }

                    const result = await response.text();
                    setName(result);
                } catch (error) {
                    console.error("Error :", error.message);
                }
            }
        }

        fetchData();
    }, [authData]);
    
    const handleProfileNavigate = (event)=>{
        event.stopPropagation();
        navigateTo("/profile");
    }

    const handleHomeNavigate = (event)=>{
        event.stopPropagation();
        navigateTo("/");
    }

    const handleUploadNavigate = (event)=>{
        event.stopPropagation();
        navigateTo("/upload");
    }

    return (
        <div className="user-info-section">
            <p>{name}</p>
            <div className='nav-button-section'>
                <div onClick={handleProfileNavigate}><img src={profile} alt='Profile'></img></div>
                <div onClick={handleHomeNavigate}><img src={home} alt='Home'></img></div>
                <div onClick={handleUploadNavigate}><img src={upload} alt='Upload'></img></div>
            </div>
        </div>
    );
}
