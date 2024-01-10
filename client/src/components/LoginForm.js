import './LoginForm.css';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { encryptText, saveUser } from '../private/Secured';

export default function LoginForm(){

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [warning, setWarning] = useState('');
    const navigateTo = useNavigate();

// Function to create form data
    function createLoginData(mail, pass){
    
        const createdData = {
            email : mail,
            password : encryptText(pass)
        }
        return createdData;
    }

// Function to handle input change

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

// Fetch function for login
    async function userLogin(data){
        const url = "http://localhost:8080/login";
        try{
            const response = await fetch(url,{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf8',
                },
                body: JSON.stringify(data),
            });

            if(!response.ok){
                throw new Error(`${response.status}`);
            }
            return response;

        } catch(error){
            console.error("Error :", error.message);
            setWarning(error.message);
        }
    }

    const handleFormSubmit = async (event)=>{
        event.preventDefault();
        const formData = createLoginData(email, password);
        const serverResponse = await userLogin(formData);

        if(serverResponse.ok){
            const result = await serverResponse.json();
        
            if(result.login === true){
                event.target.reset();
                saveUser(formData);
                navigateTo('/');
                window.location.reload();
            }
            else{
                setWarning("Invalid email or password!");
            }

        }
        else{
            setWarning("Response Status :"+ serverResponse.status);
        }

    }

    return(
        <div className="login-section-container">
            <h3>Log in</h3>
            <form className="login-form" onSubmit={handleFormSubmit}>
                <input type="email" name="email" placeholder="Email" onChange={handleEmailChange}></input>
                <input type="password" name="password" placeholder="Password" onChange={handlePasswordChange}></input>
                <button type="submit">Log in</button>
            </form>
            <p> {warning}</p>
        </div>
    );
}