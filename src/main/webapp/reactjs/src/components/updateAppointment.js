import React, {useEffect, useState} from "react";
import {useHistory, useLocation} from "react-router-dom";
import $ from 'jquery';
import {AppointmentService} from "../services/AppointmentService";
import {DoctorService} from "../services/DoctorService";
import {NurseService} from "../services/NurseService";


const UpdateAppointment = () => {
    const location = useLocation();

    const [appointment, setAppointment] = useState({
        id: '',
        status:'',
        date:'',
        start:'',
        end:'',
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


    // async function fetchDoctors() {
    //     const res = await fetch("http://localhost:8080/allDoctors");
    //     res
    //         .json()
    //         .then(res => setDoctors(res))
    //         .catch(err => setErrors(err));
    // }

    async function fetchDoctors() {
        try {
            const response = await DoctorService.getAll()
            setDoctors(response.data)
        } catch (error) {
            console.error(`Error loading doctors !: ${error}`);
        }
    }

    // async function fetchNurses() {
    //     const res = await fetch("http://localhost:8080/allNurses");
    //     res
    //         .json()
    //         .then(res => setNurses(res))
    //         .catch(err => setErrors(err));
    // }

    async function fetchNurses() {
        try {
            const response = await NurseService.getAll()
            setNurses(response.data)
        } catch (error) {
            console.error(`Error loading nurses !: ${error}`);
        }
    }

    async function fetchData() {
        try {
            const response = await AppointmentService.get(location.state.detail)
            setAppointment(response.data)
        } catch (error) {
            console.error(`Error loading appointment !: ${error}`);
        }
    }

    // async function fetchData() {
    //     const res = await fetch("http://localhost:8080/appointment/" + location.state.detail);
    //     res
    //         .json()
    //         .then(res => setAppointment(res))
    //         .catch(err => setErrors(err));
    // }


     $('#doctor').val(JSON.stringify(appointment.doctor));
     $('#nurse').val(JSON.stringify(appointment.nurse));

    const sendData = ()  => {

        let date = document.getElementById('date').value;
        let start = document.getElementById('start');
        if(start.valueAsDate != null) {
            start = start.valueAsDate.toJSON().slice(-13, -8)
        }


        let end = document.getElementById('end');
        if(end.valueAsDate != null) {
            end = end.valueAsDate.toJSON().slice(-13, -8)
        }
        // let duration = document.getElementById('duration').value;
        let doctor = document.getElementById('doctor').value;
        let nurse = document.getElementById('nurse').value;
        let price = document.getElementById('price').value;



        if(valid(date,start,end,price)) {

            let appointmentModified = {"appointment_id": appointment.appointment_id,
                "date": date, "start": start, "end": end, "status": appointment.status,
                "doctor": {"id": JSON.parse(doctor).id}, "nurse": {"id": JSON.parse(nurse).id},
                "price": price
            };

           // let appointmentModified = {...appointment}

            // console.log(appointment);
            // console.log(JSON.stringify(appointmentModified));
            console.log(appointment)
            console.log(appointmentModified);
            update(appointmentModified.appointment_id, appointmentModified)

            let path = `/appointments`;
            history.push(path);

        }

    }
    async function update(id, appointment) {
        try {
            await AppointmentService.update(id, appointment)
        } catch (error) {
            console.error(`Error ocurred while updating the appointment: ${error}`);
        }
    }

    const valid = (date, start, end, price) => {
        if(date === null || start === null || end === null || price === "") {
            alert("Make sure to fill all fields!")
            return false;
        }
        if(price < 1) {
            alert("Price should be a positive number!")
            return false;
        }
        return true;
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setAppointment({ ...appointment, [name]: val });
    }


    return (
        <>


            <h1 style={{textAlign:'center', margin:'20px'}}>Modify an appointment</h1>

            <div style={{margin: '0 auto', display: 'flex',
                justifyContent: 'center'}}>

                <div className="register-form">

                    <label htmlFor="date" className="label-register">Date:</label>
                    <input defaultValue={appointment.date} onChange={handleFormInputChange("date")} id="date" type="date"  className="input-register"/>

                    <label htmlFor="start" className="label-register">Start time:</label>
                    <input defaultValue={appointment.start} onChange={handleFormInputChange("start")} required id="start" type="time"  min="09:00" max="18:00" className="input-register"/>


                    <label htmlFor="end" className="label-register">End time:</label>
                    <input defaultValue={appointment.end} required onChange={handleFormInputChange("end")} id="end" type="time"  min="09:00" max="18:00" className="input-register"/>

                    <label htmlFor="doctor" className="label-register">Doctor:</label>
                    <select name="doctor" id="doctor" className="input-register">
                        {doctors.map((doctor) =>
                            <option key={doctor.id} value={JSON.stringify(doctor)}>{doctor.user.name + " " + doctor.user.lastName}</option>
                        )}
                    </select>

                    <label htmlFor="nurse" className="label-register">Nurse:</label>
                    <select name="nurse" id="nurse" className="input-register">
                        {nurses.map((nurse) =>
                            <option key={nurse.id} value={JSON.stringify(nurse)}>{nurse.user.name + " " + nurse.user.lastName}</option>
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