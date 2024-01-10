import { useState } from 'react';
import './UploadForm.css';
import  { getUser } from '../private/Secured'

export default function UploadForm(){
    const [msg, setMsg] = useState('');
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [content, setContent] = useState('');
    const authData = JSON.parse(getUser());
    
    //Function to get date
    function postDate() {
        const months = [
          'JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAY', 'JUNE',
          'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER'
        ];
    
        const date = new Date();
        
      
        const day = date.getDate();
        const month = months[date.getMonth()];
        const year = date.getFullYear();
      
        return (`${day} ${month} ${year}`);
    }

    //Function to create postingData
    function createPostingData(e, p, t, d, c){
        return({
            auth : {
                email : e,
                password : p
            },
            post : {
                title : t,
                description : d,
                content : c,
                date : postDate()
            }
        });
    }

    // Functions for input change
    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };
    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };
    const handleContentChange = (event) => {
        setContent(event.target.value);
    };

    // Fetch function
    async function uploadPost(data){
        const url = "http://localhost:8080/upload";
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
            setMsg(error.message);
        }
    }

    // Handle submit
    const handleUpload = async (event)=>{
        event.preventDefault();
        const postData = createPostingData(authData.email, authData.password, title, description, content);
        const serverResponse = await uploadPost(postData);

        if(serverResponse.ok){
            const result = await serverResponse.json();
        
            if(result.post === true){
                event.target.reset();
                setMsg("Article Uploaded successfully!");
                
            }
            else{
                setMsg("Something went wrong!");
            }

        }
        else{
            setMsg("Something went wrong!");
        }

    }

    return(
        <div class="upload-section-container">
            <h3>Upload Article</h3>
            <form class="registration-form" onSubmit={handleUpload}>
                <input type="text" name="title" placeholder="Title" onChange={handleTitleChange}></input>
                <input type="text" name="description" placeholder="Short Description" onChange={handleDescriptionChange}></input>
                <textarea name="content" placeholder="Main Content" onChange={handleContentChange}></textarea>
                <button type="submit">Upload</button>
            </form>
            <p>{msg}</p>
        </div>
    );
}