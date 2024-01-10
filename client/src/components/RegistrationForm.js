import './RegistrationForm.css';
import React, {useState} from 'react';
import { encryptText } from '../private/Secured';

export default function RegistrationForm(){

    const [fullname, setFullname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [cpassword, setCpassword] = useState('');

    const [warning, setWarning] = useState('');

  // Functions for input change
    const handleFullnameChange = (event) => {
        setFullname(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleCpasswordChange = (event) => {
        setCpassword(event.target.value);
    };


    // Function to create form data
    function createFormData(fname, mail, pass){
 
        const createdData = {
            fullname : fname,
            email : mail,
            password : encryptText(pass)
        }
        return createdData;
    }

    // Fetch function
    async function registerUser(data){
        const url = "http://localhost:8080/reg";
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

    // Function to submit form
    const handleFormSubmit = async (event)=>{
        event.preventDefault();
        
        // Checking Pass and CPass
        if(password === cpassword){
          const formData =  createFormData(fullname, email, password);

          const serverResponse = await registerUser(formData);

          if(serverResponse.ok){
                const result = await serverResponse.json();
                if(result.email === true){
                    setWarning("This email is already registered!");
                }
                if(result.registration === true){
                    setWarning("User registered successfully!");
                    event.target.reset();
                }
          }
          else{
            setWarning("Response Status :"+serverResponse.status);
          }
        }
        else{
            setWarning("Confirm the password carefully!");
        }
    }





    return(
        <div className="reg-section-container">
            <h3>Registration</h3>
            <form className="registration-form" autoComplete='off' onSubmit={handleFormSubmit}>
                <input type="text" name="fullname" placeholder="Full Name" onChange={handleFullnameChange}></input>
                <input type="email" name="email" placeholder="Email" onChange={handleEmailChange}></input>
                <input type="password" name="password" placeholder="Password" onChange={handlePasswordChange}></input>
                <input type="text" name="confirm" placeholder="Confirm Password" onChange={handleCpasswordChange}></input>
                <button type="submit">Register</button>
            </form>
            <p>{warning}</p>
        </div>
    );
}