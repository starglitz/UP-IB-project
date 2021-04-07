import React, {Component} from 'react';
import  profil_img from '../profil-img.png'

class PatientProfilLayout extends Component {

    state = {
        loading: true,
        patient: null,
        disabled: true
    }

    async componentDidMount() {
        const url = "http://localhost:8080/patient/1";
        const response = await fetch(url);
        const data = await response.json();
        this.setState({loading: false, patient: data});
        console.log(this.state.patient)
    }

    handleEnableClik() {
        this.setState( {disabled: !this.state.disabled} )
    }

    changeInputHandler = (event, prop) => {

        const person = {
            ...this.state.patient
        };
        person.[prop] = event.target.value;

        this.setState({patient: person});
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
                    <input id="email" type="text" value={this.state.patient.email}   className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'email')}/>

                    <label htmlFor="name" className="label-profilInfo">Name:</label>
                    <input id="name" type="text" value={this.state.patient.name} className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'name')}/>

                    <label htmlFor="surname" className="label-profilInfo">Surame:</label>
                    <input id="surname" type="text"  value={this.state.patient.lastName} className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'lastName')}/>

                    <label htmlFor="address" className="label-profilInfo">Home address:</label>
                    <input id="address" type="text" value={this.state.patient.address} className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'address')}/>

                    <label htmlFor="city" className="label-profilInfo">City:</label>
                    <input id="city" type="text"  value={this.state.patient.city} className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'city')}/>

                    <label htmlFor="state" className="label-profilInfo">State:</label>
                    <input id="state" type="text" value={this.state.patient.country} className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'country')}/>

                    <label htmlFor="contact" className="label-profilInfo">Contact:</label>
                    <input id="contact" type="text" value={this.state.patient.phoneNumber} className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'phoneNumber')}/>

                    <label htmlFor="lbo" className="label-profilInfo">LBO:</label>
                    <input id="lbo" type="text" value={this.state.patient.lbo}  className="input-profilInfo "
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           onChange={(event) => this.changeInputHandler(event, 'lbo')}/>

                    <button  onClick = {this.handleEnableClik.bind(this)}  className="submit-register"> Edit </button>

                </div>
            </>
        );
    }
}

export default PatientProfilLayout;