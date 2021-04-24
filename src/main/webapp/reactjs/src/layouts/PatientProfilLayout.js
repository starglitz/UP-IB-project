import React, {Component} from 'react';
import  profil_img from '../profil-img.png'
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';

class PatientProfilLayout extends Component {

    state = {
        loading: true,
        patient: null,
        disabled: true
    }

    async componentDidMount() {
        const url = "http://localhost:8080/patient/2";
        const response = await fetch(url);
        const data = await response.json();
        this.setState({loading: false, patient: data});
        console.log(this.state.patient)
    }

    handleEnableClik() {
        this.setState( {disabled: !this.state.disabled} )
        let saveBtn = document.getElementById('save-btn');
        if (saveBtn.style.display === "initial") {
            saveBtn.style.display = "none";
        } else {
            saveBtn.style.display = "initial";
        }
    }

    changeInputHandler = (event, prop) => {

        const person = {
            ...this.state.patient
        };
        person[prop] = event.target.value;

        this.setState({patient: person});
    }


    validateForm = (email, newPass, confirmPass, name, surname, address, city, state, contact, lbo)  => {
        let ok = true;
        if(email === "" || name === "" || surname === "" || address === ""
            || city === "" || state === "" || contact === "" || lbo === "") {
            ok = false;
            alert("Make sure to fill all fields!")
        }
        else if(newPass !== "" && newPass.length < 8) {
            ok = false;
            alert("Password should be at least 8 characters long! 😡")
        }
        else if(newPass !== confirmPass) {
            ok = false;
            alert("Passwords don't match!")
        }


        else if(this.validateEmail(email) === false) {
            ok = false;
            alert("You have entered an invalid email address!")
        }

        return ok;
    }


    validateEmail = (email)  => {
        let mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if(email.match(mailformat)) {
            return true;
        }
        return false;
    }

    handleSaveData = ()  => {
        let email = document.getElementById('email').value;
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;
        let address = document.getElementById('address').value;
        let city = document.getElementById('city').value;
        let state = document.getElementById('state').value;
        let contact = document.getElementById('contact').value;
        let lbo = document.getElementById('lbo').value;
        let newPass = document.getElementById('newPassword').value;
        let confirmPass = document.getElementById('confirmPass').value;

        if(this.validateForm(email, newPass, confirmPass, name,surname,address,city,state,contact,lbo)) {


            let user = {"email":email, "password":0, "name":name, "lastName":surname,
                "address":address, "city":city, "country":state, "phoneNumber":contact, "lbo":lbo, "enabled":true};
            if(newPass !== ""){
                user.password = newPass;
            }
            fetch('http://localhost:8080/patient/2', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(user),
            })
                .then(response => response.json())
                .then(user => {
                    console.log('Success:', user);
                    window.location.reload();
                })
                .catch((error) => {
                    console.error('Error:', error);
                });

        }
        else {
            alert('fail')
        }
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
        const btnStyle = {
            display: 'block',
            margin: '10px auto 10px auto',
            textAlign: 'center'
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
                           disabled/>

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
                           disabled/>

                    <label htmlFor="newPassword" className="label-profilInfo">New password:</label>
                    <input id="newPassword" type="text"   className="input-profilInfo " placeholder="Enter new password"
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           />

                    <label htmlFor="confirmPass" className="label-profilInfo">Confirm password:</label>
                    <input id="confirmPass" type="text"  className="input-profilInfo " placeholder="Enter new password again"
                           disabled = {(this.state.disabled)? "disabled" : ""}
                           />

                    <div style={btnStyle}>
                        <Button  onClick = {this.handleEnableClik.bind(this)}
                                 variant="contained" color="primary"  size="medium"> Edit </Button>

                        <Button id="save-btn" onClick={this.handleSaveData}
                                variant="contained"
                                color="primary"
                                size="medium"
                                style={{margin: '15px', display: 'none', backgroundColor: '#d60808'}}
                                startIcon={<SaveIcon />}>Save</Button>
                    </div>


                </div>
            </>
        );
    }
}

export default PatientProfilLayout;