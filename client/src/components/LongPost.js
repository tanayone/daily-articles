import './LongPost.css';
import userImg from '../components/img/user.png';
import placeholder from '../components/img/placeholder.jpg';

export default function LongPost({author, title, content, date}){
    return(
        <div className="content-container-main">
            <div className="content-container-top">
                <div className="content-details">
                    <div className="author-details">
                        <img src={userImg} alt=''></img>
                        <h4>{author}</h4>
                    </div>
                    <p className="content-post-time">{date}</p>
                </div>
            </div>
            <img className="content-image" src={placeholder} alt=''></img>
            <div className="content-container-bottom">
                <h2 className="content-title">{title}</h2>
                <p className="main-text-content">
                    {content}
                </p>
            </div>
        </div>
    );
}