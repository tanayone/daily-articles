import './ConfirmMsg.css';

export default function ConfirmMsg({msg, functionOne, functionTwo}){
    const HideConfirmMsg = functionOne;
    const DeletePost = functionTwo;

    return(
        <div className='confirmation-container'>
            <div className='confirmation-box'>
                <p>Do you want to delete the post?</p>
                <div className='confirmation-button-container'>
                    <button onClick={HideConfirmMsg}>CANCEL</button>
                    <button onClick={DeletePost}>OK</button>
                </div>
            </div> 
        </div>
    );
}