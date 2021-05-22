import React, {Component, useEffect, useState} from 'react';
import  profil_img from '../assets/profil-img.png'
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';
import {useLocation} from "react-router-dom";
import {PatientService} from "../services/PatientService";
import {AppointmentService} from "../services/AppointmentService";

const  DEFAULT_PATIENT = {
    approved: false,
    enabled: true,
    id: 1,
    lbo: "",
    patientBookId: 2,
    user: {
        address: "",
        city: "",
        country: "",
        email: "",
        id: 1,
        lastName: "",
        lastPasswordResetDate: null,
        name: "",
        password: "",
        phoneNumber: "",
    }
}

const PatientProfilLayout = () => {



    const [loading, setLoading] =  useState(true);
    const [patient, setPatient] =  useState(DEFAULT_PATIENT);
    const [disabled, setDisabled] =  useState(true);

    // async componentDidMount() {
    //     const url = "http://localhost:8080/patient/2";
    //     const response = await fetch(url);
    //     const data = await response.json();
    //     this.setState({loading: false, patient: data});
    //     console.log(patient)
    // }

    useEffect(() => {
        fetchPatient();
    }, []);

    async function fetchPatient() {
        try {
            const response = await PatientService.get(1);
            setPatient(response.data);
            setLoading(false);
            console.log(response.data);
        } catch (error) {
            console.error(`Error loading your profile !: ${error}`);
        }
    }


    const handleEnableClik = () => {
         setDisabled(!disabled)
        let saveBtn = document.getElementById('save-btn');
        if (saveBtn.style.display === "initial") {
            saveBtn.style.display = "none";
        } else {
            saveBtn.style.display = "initial";
        }
    }

    const changeInputHandler = (event, prop) => {

        const person = {
            ...patient
        };

        const user = {
            ...patient.user
        };


        user[prop] = event.target.value;

        person.user = user;

        setPatient(person);


    }

    console.log({...patient.user})
    const validateForm = (email, newPass, confirmPass, name, surname, address, city, state, contact, lbo)  => {
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


        else if(validateEmail(email) === false) {
            ok = false;
            alert("You have entered an invalid email address!")
        }

        return ok;
    }


    const validateEmail = (email)  => {
        let mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if(email.match(mailformat)) {
            return true;
        }
        return false;
    }

    async function update(id, appointment) {
        try {
            await PatientService.update(id, patient)
            window.location.reload();
        } catch (error) {
            console.error(`Error ocurred while updating the appointment: ${error}`);
        }
    }

    const handleSaveData = ()  => {
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

        if(validateForm(email, newPass, confirmPass, name,surname,address,city,state,contact,lbo)) {


            let user = {"email":email, "password":0, "name":name, "lastName":surname,
                "address":address, "city":city, "country":state, "phoneNumber":contact, "lbo":lbo, "enabled":true};
            if(newPass !== ""){
                user.password = newPass;
            }

            update(1,patient);


        }
        else {
            alert('fail')
        }
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

        if (loading) {
            return <div style={{textAlign:'center'}}>loading...</div>;
        }

        if (!patient) {
            return <div style={{textAlign:'center'}}>Can't find your profile :/</div>;
        }

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
                    <input id="email" type="text" value={patient.user.email}   className="input-profilInfo "
                           disabled/>

                    <label htmlFor="name" className="label-profilInfo">Name:</label>
                    <input id="name" type="text" value={patient.user.name} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'name')}/>

                    <label htmlFor="surname" className="label-profilInfo">Surame:</label>
                    <input id="surname" type="text"  value={patient.user.lastName} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'lastName')}/>

                    <label htmlFor="address" className="label-profilInfo">Home address:</label>
                    <input id="address" type="text" value={patient.user.address} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'address')}/>

                    <label htmlFor="city" className="label-profilInfo">City:</label>
                    <input id="city" type="text"  value={patient.user.city} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'city')}/>

                    <label htmlFor="state" className="label-profilInfo">State:</label>
                    <input id="state" type="text" value={patient.user.country} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'country')}/>

                    <label htmlFor="contact" className="label-profilInfo">Contact:</label>
                    <input id="contact" type="text" value={patient.user.phoneNumber} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'phoneNumber')}/>

                    <label htmlFor="lbo" className="label-profilInfo">LBO:</label>
                    <input id="lbo" type="text" value={patient.lbo}  className="input-profilInfo "
                           disabled/>

                    <label htmlFor="newPassword" className="label-profilInfo">New password:</label>
                    <input id="newPassword" type="text"   className="input-profilInfo " placeholder="Enter new password"
                           disabled = {(disabled)? "disabled" : ""}
                           />

                    <label htmlFor="confirmPass" className="label-profilInfo">Confirm password:</label>
                    <input id="confirmPass" type="text"  className="input-profilInfo " placeholder="Enter new password again"
                           disabled = {(disabled)? "disabled" : ""}
                           />

                    <div style={btnStyle}>
                        <Button  onClick = {handleEnableClik}
                                 variant="contained" color="primary"  size="medium"> Edit </Button>

                        <Button id="save-btn" onClick={handleSaveData}
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

export default PatientProfilLayout;