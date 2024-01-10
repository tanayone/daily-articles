import './Home.css';
import NavBar from '../components/NavBar';
import AllPosts from '../components/AllPosts';
import Landing from '../components/Landing';
import Footer from '../components/Footer';

export default function Home(){
    return(
        <div className='home-page-container'>
            <NavBar/>
            <Landing/>
            <AllPosts/>
            <Footer/>
        </div>
    );
}