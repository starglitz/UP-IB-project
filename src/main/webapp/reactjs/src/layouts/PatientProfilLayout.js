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
    userDto: {
        address: "",
        city: "",
        country: "",
        email: "",
        id: 1,
        lastName: "",
        lastPasswordResetDate: null,
        name: "",
        phoneNumber: "",
    }
}

const PatientProfilLayout = () => {



    const [loading, setLoading] =  useState(true);
    const [patient, setPatient] =  useState({DEFAULT_PATIENT});
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
            const response = await PatientService.getLogged();
            setPatient(response.data);
            setLoading(false);
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
            ...patient.userDto
        };


        user[prop] = event.target.value;

        person.userDto = user;

        setPatient(person);


    }

    const validateForm = (name, surname, address, city, state, contact)  => {
        let ok = true;
        if(name === "" || surname === "" || address === ""
            || city === "" || state === "" || contact === "") {
            ok = false;
            alert("Make sure to fill all fields!")
        }

        return ok;
    }



    async function update(id, patient) {
        try {
            await PatientService.update(patient.id, patient)
            window.location.reload();
        } catch (error) {
            console.error(`Error ocurred while updating the appointment: ${error}`);
        }
    }

    const handleSaveData = ()  => {
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;
        let address = document.getElementById('address').value;
        let city = document.getElementById('city').value;
        let state = document.getElementById('state').value;
        let contact = document.getElementById('contact').value;


        if(validateForm(name,surname,address,city,state,contact)) {


            let user = {"name":name, "lastName":surname,
                "address":address, "city":city, "country":state, "phoneNumber":contact};


            const newUser = {
                ...patient.userDto
            }



            function updateKeyIfDifferentValue(obj, src) {
                Object.keys(obj).forEach(function(key) {
                    if (src.hasOwnProperty(key)) {
                        obj[key] = src[key];
                    } else {
                        obj[key] = obj[key];
                    }
                });
                return obj;
            }

            const t = updateKeyIfDifferentValue(newUser, user);

            patient.userDto = newUser;


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
                    <input id="email" type="text" value={patient.userDto.email}   className="input-profilInfo "
                           disabled/>

                    <label htmlFor="name" className="label-profilInfo">Name:</label>
                    <input id="name" type="text" value={patient.userDto.name} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'name')}/>

                    <label htmlFor="surname" className="label-profilInfo">Surame:</label>
                    <input id="surname" type="text"  value={patient.userDto.lastName} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'lastName')}/>

                    <label htmlFor="address" className="label-profilInfo">Home address:</label>
                    <input id="address" type="text" value={patient.userDto.address} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'address')}/>

                    <label htmlFor="city" className="label-profilInfo">City:</label>
                    <input id="city" type="text"  value={patient.userDto.city} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'city')}/>

                    <label htmlFor="state" className="label-profilInfo">State:</label>
                    <input id="state" type="text" value={patient.userDto.country} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'country')}/>

                    <label htmlFor="contact" className="label-profilInfo">Contact:</label>
                    <input id="contact" type="text" value={patient.userDto.phoneNumber} className="input-profilInfo "
                           disabled = {(disabled)? "disabled" : ""}
                           onChange={(event) => changeInputHandler(event, 'phoneNumber')}/>

                    <label htmlFor="lbo" className="label-profilInfo">LBO:</label>
                    <input id="lbo" type="text" value={patient.lbo}  className="input-profilInfo "
                           disabled/>


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