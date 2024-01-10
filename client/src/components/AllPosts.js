import './AllPosts.css';
import { useState, useEffect } from 'react';
import ShortPost from './ShortPost';

export default function AllPosts() {
    const [data, setData] = useState([]);

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await fetch("http://localhost:8080/posts");
                const result = await response.json();
                setData(result);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        }

        fetchData();
    }, []);

    return (
        <div className='container-for-posts'>
            {data.map(setPostData)}
        </div>
    );
}

function setPostData(post) {
    return (
            <ShortPost date={post.date} title={post.title} description={post.description} author={post.author} pid={post.pid} />
    );
}