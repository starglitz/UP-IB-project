import React, {Component} from 'react';
import  profil_img from '../profil-img.png'

class PatientProfilLayout extends Component {
    render() {

        const imgStyle = {
            display: 'block',
            margin: '20px auto 20px auto'
            };
        return (
            <>
                <h3 style={{textAlign:'center', margin:'20px', textTransform:'uppercase'}}>My profile</h3>
                <hr/>
                <img
                    src={profil_img}
                    width="200"
                    height="200"
                    style={imgStyle}
                    alt="hospital logo"
                />

                <div>


                    <label htmlFor="email" className="label-profilInfo">Email:</label>
                    <input id="email" type="text"  className="input-profilInfo " disabled={true}/>

                    <label htmlFor="name" className="label-profilInfo">Name:</label>
                    <input id="name" type="text"  className="input-profilInfo " disabled={true}/>

                    <label htmlFor="surname" className="label-profilInfo">Surame:</label>
                    <input id="surname" type="text"  className="input-profilInfo " disabled={true}/>

                    <label htmlFor="address" className="label-profilInfo">Home address:</label>
                    <input id="address" type="text" className="input-profilInfo " disabled={true}/>

                    <label htmlFor="city" className="label-profilInfo">City:</label>
                    <input id="city" type="text"  className="input-profilInfo " disabled={true}/>

                    <label htmlFor="state" className="label-profilInfo">State:</label>
                    <input id="state" type="text" className="input-profilInfo " disabled={true}/>

                    <label htmlFor="name" className="label-profilInfo">Name:</label>
                    <input id="name" type="text"  className="input-profilInfo " disabled={true}/>

                    <label htmlFor="contact" className="label-profilInfo">Contact:</label>
                    <input id="contact" type="text" className="input-profilInfo " disabled={true}/>

                    <label htmlFor="lbo" className="label-profilInfo">LBO:</label>
                    <input id="lbo" type="text"  className="input-profilInfo " disabled={true}/>

                </div>
            </>
        );
    }
}

export default PatientProfilLayout;