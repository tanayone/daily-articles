import './Profile.css';
import NavBar from '../components/NavBar';
import ProfileInfo from '../components/ProfileInfo';
import MyAllPosts from '../components/MyAllPosts';
import Footer from '../components/Footer';

export default function Profile(){
    return(
        <div className='profile-page'>
            <NavBar/>
            <div className="profile-page-container">
                <ProfileInfo/>
                <MyAllPosts/>
            </div>
            <Footer></Footer>
        </div>
    );
}