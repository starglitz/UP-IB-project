import React, {useEffect, useState} from 'react';
import {useParams, Link} from "react-router-dom";
import hospitalImg from "../../assets/hospital-clinicProfile.png";
import {AppointmentService} from "../../services/AppointmentService";
import {ClinicService} from "../../services/ClinicService";

function ClinicProfile(){

    const [clinic, setClinic] = useState({

        clinic_id: '',
        name: '',
        description: '',
        rating: '',
        priceList: []
    });


    const [hasError, setErrors] =  useState(false);

    useEffect(() => {
        fetchData()
    }, []);

    const {id} = useParams();

    // async function fetchData() {
    //     const res = await fetch("http://localhost:8080/clinic/" + id);
    //     res
    //         .json()
    //         .then(res => setClinic(res))
    //         .catch(err => setErrors(err));
    // }


    async function fetchData() {
        try {
            const response = await ClinicService.get(id);
            setClinic(response.data);
        } catch (error) {
            console.error(`Error loading clinic !: ${error}`);
        }
    }

    return (
        <>




            <div className="hospitalProfile-container">

                <img src={hospitalImg}></img>
                <h1>Hospital profile</h1>




                    <ul>
                        <li className="info-row">
                            <p>Name:</p>
                            <p>{clinic.name}</p>
                        </li>
                        <li className="info-row">
                            <p>Description:</p>
                            <p>{clinic.description}</p>
                        </li>

                        <li className="info-row">
                            <p>Rating:</p>
                            <p>{clinic.rating}</p>
                        </li>

                    </ul>

                <div className="link-appointments">
                    <Link to={"/clinicAppointments/" + id}>Appointments</Link>
                </div>

            </div>


        </>
    )

}

export default ClinicProfile;