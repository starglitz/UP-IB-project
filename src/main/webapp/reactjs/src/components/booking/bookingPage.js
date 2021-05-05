import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import Button from "@material-ui/core/Button";

function BookingPage() {


    const [appointment, setAppointment] = useState({
        appointment_id: '',
        doctor:'',
        date: '',
        start: '',
        price: ''
    })
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            .then(res => setAppointment(res))
            .catch(err => setError(err));
    },[])

    const {id} = useParams();

    async function fetchData() {
        const res = await fetch('http://localhost:8080/appointment/' + id);
        return res.json()
    }


    return (
        <>

            <div className="hospitalProfile-container">

                <h1>Book appointment</h1>


                <ul>
                    <li className="info-row">
                        <p>Doctor name:</p>
                        <p>{appointment.doctor.name}</p>
                    </li>
                    <li className="info-row">
                        <p>Date:</p>
                        <p>{appointment.date}</p>
                    </li>

                    <li className="info-row">
                        <p>Start at:</p>
                        <p>{appointment.start}</p>
                    </li>

                    <li className="info-row">
                        <p>Price:</p>
                        <p>{appointment.price}</p>
                    </li>

                </ul>

                <div className="link-appointments">
                    <Button type='submit' variant="contained" size="large" color="default">Book</Button>
                </div>

            </div>


        </>
    );
}

export default BookingPage;