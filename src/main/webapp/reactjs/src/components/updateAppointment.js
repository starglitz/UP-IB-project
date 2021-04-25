import React, {useEffect, useState} from "react";
import {useHistory, useLocation} from "react-router-dom";
import $ from 'jquery';


const UpdateAppointment = () => {
    const location = useLocation();

    const [appointment, setAppointment] = useState({
        id: '',
        status:'',
        date:'',
        time:'',
        duration:'',
        doctor: {},
        nurse: {},
        price:'',
    });
    const [hasError, setErrors] =  useState(false);

    const [doctors, setDoctors] = useState([]);
    const [nurses, setNurses] = useState([]);

    const history = useHistory();


    useEffect(() => {
        console.log(location.pathname); // result: '/secondpage'
        console.log(location.search); // result: '?query=abc'
        console.log(location.state.detail);
        fetchData()// result: 'some_value'
        fetchDoctors();
        fetchNurses();
        console.log(appointment)
    }, [location]);


    async function fetchDoctors() {
        const res = await fetch("http://localhost:8080/allDoctors");
        res
            .json()
            .then(res => setDoctors(res))
            .catch(err => setErrors(err));
    }

    async function fetchNurses() {
        const res = await fetch("http://localhost:8080/allNurses");
        res
            .json()
            .then(res => setNurses(res))
            .catch(err => setErrors(err));
    }

    async function fetchData() {
        const res = await fetch("http://localhost:8080/appointment/" + location.state.detail);
        res
            .json()
            .then(res => setAppointment(res))
            .catch(err => setErrors(err));
    }
     $('#doctor').val(JSON.stringify(appointment.doctor));
     $('#nurse').val(JSON.stringify(appointment.nurse));

    const sendData = ()  => {

        let date = document.getElementById('date').value;
        let time = document.getElementById('time');
        if(time.valueAsDate != null) {
            time = time.valueAsDate.toJSON().slice(-13, -8)
        }
        let duration = document.getElementById('duration').value;
        let doctor = document.getElementById('doctor').value;
        let nurse = document.getElementById('nurse').value;
        let price = document.getElementById('price').value;



        if(valid(date,time,duration,price)) {

            let appointmentModified = {"appointment_id": appointment.appointment_id,
                "date": date, "time": time, "duration": duration,
                "doctor": {"id": JSON.parse(doctor).id}, "nurse": {"id": JSON.parse(nurse).id},
                "price": price
            };
            console.log(appointment);
            console.log(JSON.stringify(appointmentModified));
            fetch('http://localhost:8080/updateAppointment', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(appointmentModified),
            })
                // .then(response => response.json())
                .then(user => {
                    console.log('Success:', user);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });

            let path = `/appointments`;
            history.push(path);

        }

    }

    const valid = (date, time, duration, price) => {
        if(date === null || time === null || duration === "" || price === "") {
            alert("Make sure to fill all fields!")
            return false;
        }
        if(price < 1) {
            alert("Price should be a positive number!")
            return false;
        }
        if(duration < 1) {
            alert("Duration should be a positive number!")
            return false;
        }
        return true;
    }


    return (
        <>


            <h1 style={{textAlign:'center', margin:'20px'}}>Modify an appointment</h1>

            <div style={{margin: '0 auto', display: 'flex',
                justifyContent: 'center'}}>

                <div className="register-form">

                    <label htmlFor="date" className="label-register">Date:</label>
                    <input defaultValue={appointment.date} id="date" type="date"  className="input-register"/>

                    <label htmlFor="time" className="label-register">Time:</label>
                    <input defaultValue={appointment.time} required id="time" type="time"  min="09:00" max="18:00" className="input-register"/>

                    <label htmlFor="duration" className="label-register">Duration:</label>
                    <input defaultValue={appointment.duration} id="duration" type="text" placeholder="enter duration in minutes or hours" className="input-register"/>

                    <label htmlFor="doctor" className="label-register">Doctor:</label>
                    <select name="doctor" id="doctor" className="input-register">
                        {doctors.map((doctor) =>
                            // JSON.stringify(doctor)
                            <option key={doctor.id} value={JSON.stringify(doctor)}>{doctor.name + " " + doctor.lastName}</option>
                        )}
                    </select>

                    <label htmlFor="nurse" className="label-register">Nurse:</label>
                    <select name="nurse" id="nurse" className="input-register">
                        {nurses.map((nurse) =>
                            <option key={nurse.id} value={JSON.stringify(nurse)}>{nurse.name + " " + nurse.lastName}</option>
                        )}
                    </select>

                    <label htmlFor="price" className="label-register">Price:</label>
                    <input defaultValue={appointment.price} id="price" type="text" placeholder="enter price" className="input-register"/>

                    <button className="submit-register" onClick={sendData}>Submit</button>
                </div>
            </div>

        </>
    )
}

export default UpdateAppointment;