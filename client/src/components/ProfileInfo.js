import { useNavigate } from 'react-router-dom';
import './ProfileInfo.css';
import { useState, useEffect } from 'react';
import { getUser } from '../private/Secured';
import userImg from '../components/img/user.png'

export default function ProfileInfo(){

    const [fullName, setFullName] = useState('');
    const [email, setEmail] = useState('');
    const authData = getUser();
    const navigateTo = useNavigate();

    useEffect(() => {
        async function fetchData() {
            if (authData !== null) {
                try {
                    const url = "http://localhost:8080/profile";
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

                    const result = await response.json();
                    if(result != null){
                        setFullName(result.fullname);
                        setEmail(result.email);
                    }
                } catch (error) {
                    console.error("Error :", error.message);
                }
            }
        }

        fetchData();
    }, [authData]);

    const handleLogOut = ()=>{
        localStorage.removeItem('authData');
        navigateTo("/");
        window.location.reload();
    }

    return(
        <div className="profile-info-holder">
            <div className="profile-container">
                <img src={userImg} alt=""></img>
                <div className="profile-details-container">
                    <h5>Name :</h5>
                    <p>{fullName}</p>
                    <h5>Email :</h5>
                    <p>{email}</p>
                </div>
                <button onClick={handleLogOut}>Log out</button>
            </div>
        </div>
    );
}