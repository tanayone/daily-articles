import './MyAllPosts.css';
import MyPost from './MyPost';
import {useState, useEffect} from 'react';
import { getUser } from '../private/Secured';

export default function MyAllPosts(){
    const authData = getUser();
    const [data, setData] = useState([]);

    useEffect(() => {
        async function fetchData() {
            try {
                const url = "http://localhost:8080/user/posts";
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
                setData(result);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        }

        fetchData();
    }, [authData]);



    return(
        <div className="my-post-cotainer">
            <h1>Your Posts</h1>
            {data.map(setPostData)}
        </div>
    );
}

function setPostData(post) {
    return (
        <MyPost date={post.date} title={post.title} description={post.description} pid={post.pid} />
    );
}