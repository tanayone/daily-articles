import './Upload.css';
import NavBar from '../components/NavBar';
import UploadForm from '../components/UploadForm';

export default function Upload(){
    return(
        <div className='upload-page-container'>
            <NavBar/>
            <div className='upload-form-container'>
                <UploadForm/>
            </div>
        </div>
    );
}