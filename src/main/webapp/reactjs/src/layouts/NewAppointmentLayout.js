import React, {useEffect, useState} from 'react';
import RegisterRequestRow from "../components/registerRequestRow";

const NewAppointmentLayout = () => {

    const [doctors, setDoctors] = useState([]);
    const [nurses, setNurses] = useState([]);
    const [hasError, setErrors] =  useState(false);

    useEffect(() => {
        fetchDoctors();
        fetchNurses();
    }, []);

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

    const sendData = ()  => {

        let date = document.getElementById('date').value;
        let start = document.getElementById('start');
        if(start.valueAsDate != null) {
            start = start.valueAsDate.toJSON().slice(-13, -8)
        }
        let end = document.getElementById('end').value;
        let doctor = document.getElementById('doctor').value;
        let nurse = document.getElementById('nurse').value;
        let price = document.getElementById('price').value;

        console.log('date: ' + date);
        console.log('start: ' + start);
        console.log('doctor: ' + JSON.parse(doctor));

        console.log('doct id: ' + JSON.parse(doctor).id);

        if(valid(date,start,end,price)) {

            let appointment = {
                "date": date, "start": start, "end": end,
                "doctor": {"id": JSON.parse(doctor).id}, "nurse": {"id": JSON.parse(nurse).id},
                "price": price
            };
            console.log(appointment);
            console.log(JSON.stringify(appointment));
            fetch('http://localhost:8080/addAppointment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(appointment),
            })
                // .then(response => response.json())
                .then(user => {
                    console.log('Success:', user);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });

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

    var curr = new Date();
    var date = curr.toISOString().substr(0,10);

    return(
        <>
            <h1 style={{textAlign:'center', margin:'20px'}}>Add new appointment</h1>

            <div style={{margin: '0 auto', display: 'flex',
                justifyContent: 'center'}}>

                <div className="register-form">

                    <label htmlFor="date" className="label-register">Date:</label>
                    <input defaultValue={date} id="date" type="date"  className="input-register"/>

                    <label htmlFor="time" className="label-register">Start time:</label>
                    <input defaultValue="09:00" required id="start" type="time" min="09:00" max="18:00" className="input-register"/>

                    <label htmlFor="duration" className="label-register">End time:</label>
                    <input  defaultValue="10:00" required id="end" type="time" min="10:00" max="19:00" className="input-register"/>

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
                    <input  id="price" type="text" placeholder="enter price" className="input-register"/>

                    <button onClick={sendData} className="submit-register">Submit</button>
                </div>
            </div>
        </>
    )
}


export default NewAppointmentLayout;



