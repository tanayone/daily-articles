import './Post.css';
import NavBar from '../components/NavBar';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import LongPost from '../components/LongPost';
import PostNotAvailable from '../components/PostNotAvailable';
import Footer from '../components/Footer';

export default function Post(){
    const {pid} = useParams();
    const [postData, setPostData] = useState({});

    useEffect(() => {
        async function fetchPostData (id) {
          try {
            const response = await fetch(`http://localhost:8080/post?id=${id}`);
            
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
    
            const data = await response.json();
            setPostData(data);

          } catch (error) {
            console.error('Error fetching data:', error);
          }
        }
    
        fetchPostData(pid);
    }, [pid]);

    return(
        <div className='post-page-container'>
            <NavBar/>
            {postData.post ? <LongPost author={postData.author} title={postData.title} content={postData.content} date={postData.date} /> : <PostNotAvailable/>}
            <Footer/>
        </div>
    );
}