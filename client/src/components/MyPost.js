import { useState } from 'react';
import ConfirmMsg from './ConfirmMsg';
import './MyPost.css';
import { useNavigate } from 'react-router-dom';
import { getUser } from '../private/Secured';
import placeholder from '../components/img/placeholder.jpg';

export default function MyPost({date, title, description, pid}){
    
    const navigateTo = useNavigate();
    const handleReadArticle = ()=>{
        navigateTo(`/post/${pid}`);
    }

    const [hide, setHide] = useState(false);

    const handleHide = ()=>{
        setHide(!hide);
    }

    const authData = JSON.parse(getUser());
    const payloadData = {
        auth : authData,
        id : pid
    }

    const deletePostFunction = async () => {
        try {
          const response = await fetch('http://localhost:8080/post/delete', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(payloadData),
          });
    
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          else{
            const data = await response.json();
            if(data.post){
                alert("Post Deleted Successfully");
                window.location.reload();
            }
            else{
                alert("Something Went Wrong!");
            }
          }
          
        } catch (error) {
          alert(error.message);
        }
        handleHide();
      }

    return(
        <div className="my-post-holder">
            <img src={placeholder} alt=''></img>
            <div className="my-post-details">
                <p className="my-post-date">{date}</p>
                <h2 className="my-post-title">{title}</h2>
                <p className="my-post-short-description">{description}</p>
            </div>
            <div className="button-section">
                <button className="read-btn" onClick={handleReadArticle}>READ</button>
                <button className="delete-btn" onClick={handleHide}>DELETE</button>
            </div>
            {hide && <ConfirmMsg functionOne={handleHide} functionTwo={deletePostFunction}/>}
        </div>
    );
}