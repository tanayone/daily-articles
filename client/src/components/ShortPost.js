import './ShortPost.css';
import { useNavigate } from 'react-router-dom';
import placeholder from '../components/img/placeholder.jpg';

export default function ShortPost({author, date, title, description, pid}){
    const navigateTo = useNavigate();
    const handleReadArticle = ()=>{
        navigateTo(`/post/${pid}`);
    }
    return(
        <div className="post-container">
            <img src={placeholder} alt=""></img>
            <div className="post-details-container">
                <p className="date-of-post">POSTED {date}</p>
                <h2 className="title-of-post">{title}</h2>
                <p className="short-description-of-post">{description}</p>
                <p className="author-of-post">- {author}</p>
            </div>
            <div className="read-article-section" onClick={handleReadArticle}>
                <p>READ ARTICLE</p>
                <p>{`>`}</p>
            </div>
        </div>
    )
}