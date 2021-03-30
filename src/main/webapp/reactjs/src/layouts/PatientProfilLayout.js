import React, {Component} from 'react';
import  profil_img from '../profil-img.png'

class PatientProfilLayout extends Component {

    state = {
        loading: true,
        patient: null
    }

    async componentDidMount() {
        const url = "http://localhost:8080/patient/1";
        const response = await fetch(url);
        const data = await response.json();
        this.setState({loading: false, patient: data});
        console.log(this.state.patient)
    }


    render() {
        if (this.state.loading) {
            return <div style={{textAlign:'center'}}>loading...</div>;
        }

        if (!this.state.patient) {
            return <div style={{textAlign:'center'}}>Can't find your profile :/</div>;
        }

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
                    <input id="email" type="text" value={this.state.patient.email}  className="input-profilInfo " disabled={true}/>

                    <label htmlFor="name" className="label-profilInfo">Name:</label>
                    <input id="name" type="text" value={this.state.patient.name} className="input-profilInfo " disabled={true}/>

                    <label htmlFor="surname" className="label-profilInfo">Surame:</label>
                    <input id="surname" type="text"  value={this.state.patient.lastName} className="input-profilInfo " disabled={true}/>

                    <label htmlFor="address" className="label-profilInfo">Home address:</label>
                    <input id="address" type="text" value={this.state.patient.address} className="input-profilInfo " disabled={true}/>

                    <label htmlFor="city" className="label-profilInfo">City:</label>
                    <input id="city" type="text"  value={this.state.patient.city} className="input-profilInfo " disabled={true}/>

                    <label htmlFor="state" className="label-profilInfo">State:</label>
                    <input id="state" type="text" value={this.state.patient.country} className="input-profilInfo " disabled={true}/>

                    <label htmlFor="contact" className="label-profilInfo">Contact:</label>
                    <input id="contact" type="text" value={this.state.patient.phoneNumber} className="input-profilInfo " disabled={true}/>

                    <label htmlFor="lbo" className="label-profilInfo">LBO:</label>
                    <input id="lbo" type="text" value={this.state.patient.lbo}  className="input-profilInfo " disabled={true}/>

                </div>
            </>
        );
    }
}

export default PatientProfilLayout;