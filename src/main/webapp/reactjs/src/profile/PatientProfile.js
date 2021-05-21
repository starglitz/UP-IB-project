import profil_img from "../assets/profil-img.png";
import Button from "@material-ui/core/Button";
import SaveIcon from "@material-ui/icons/Save";
import React, {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import {AppointmentService} from "../services/AppointmentService";
import {PatientService} from "../services/PatientService";

const PatientProfile = () => {

    const location = useLocation();

    const [patient, setPatient] = useState({
        id: '',
        address:'',
        city:'',
        country:'',
        email:'',
        lastName:'',
        name:'',
        phoneNumber:'',
        lbo:''
    });


    const [hasError, setErrors] =  useState(false);

    // async function fetchData() {
    //     const res = await fetch("http://localhost:8080/patient/" + location.state.detail);
    //     res
    //         .json()
    //         .then(res => setPatient(res))
    //         .catch(err => setErrors(err));
    // }

    async function fetchData() {
        try {
            const response = await PatientService.get(location.state.detail)
            setPatient(response.data)
        } catch (error) {
            console.error(`Error loading patient !: ${error}`);
        }
    }

    useEffect(() => {
        fetchData()
    }, [location]);

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
            <h3 style={{textAlign:'center', margin:'20px', textTransform:'uppercase'}}>Patient's profile</h3>
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
                <input id="email" type="text" value={patient.email}   className="input-profilInfo "
                       disabled/>

                <label htmlFor="name" className="label-profilInfo">Name:</label>
                <input id="name" type="text" value={patient.name} className="input-profilInfo "
                       disabled
                       onChange={(event) => this.changeInputHandler(event, 'name')}/>

                <label htmlFor="surname" className="label-profilInfo">Surame:</label>
                <input id="surname" type="text"  value={patient.lastName} className="input-profilInfo "
                       disabled
                       onChange={(event) => this.changeInputHandler(event, 'lastName')}/>

                <label htmlFor="address" className="label-profilInfo">Home address:</label>
                <input id="address" type="text" value={patient.address} className="input-profilInfo "
                       disabled
                       onChange={(event) => this.changeInputHandler(event, 'address')}/>

                <label htmlFor="city" className="label-profilInfo">City:</label>
                <input id="city" type="text"  value={patient.city} className="input-profilInfo "
                       disabled
                       onChange={(event) => this.changeInputHandler(event, 'city')}/>

                <label htmlFor="state" className="label-profilInfo">State:</label>
                <input id="state" type="text" value={patient.country} className="input-profilInfo "
                       disabled
                       onChange={(event) => this.changeInputHandler(event, 'country')}/>

                <label htmlFor="contact" className="label-profilInfo">Contact:</label>
                <input id="contact" type="text" value={patient.phoneNumber} className="input-profilInfo "
                       disabled
                       onChange={(event) => this.changeInputHandler(event, 'phoneNumber')}/>

                <label htmlFor="lbo" className="label-profilInfo">LBO:</label>
                <input id="lbo" type="text" value={patient.lbo}  className="input-profilInfo "
                       disabled/>

                <div style={btnStyle}>

                    <Button id="save-btn"
                            variant="contained"
                            color="primary"
                            size="medium"
                            style={{margin: '15px', display: 'none', backgroundColor: '#d60808'}}
                            startIcon={<SaveIcon />}>Start an appointment</Button>
                </div>


            </div>
        </>
    );
}

export default PatientProfile;