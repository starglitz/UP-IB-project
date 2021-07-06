import React, {useEffect, useState} from 'react';
import RegisterRequestRow from "../components/registerRequestRow";
import {AppointmentService} from "../services/AppointmentService";
import {DoctorService} from "../services/DoctorService";
import {NurseService} from "../services/NurseService";
import {useHistory} from "react-router-dom";

const NewAppointmentLayout = () => {

    const [doctors, setDoctors] = useState([]);
    const [nurses, setNurses] = useState([]);
    const [hasError, setErrors] =  useState(false);

    const history = useHistory()

    const [error, setError] = useState("")
    useEffect(() => {
        fetchDoctors();
        fetchNurses();
    }, []);



    async function fetchDoctors() {
        try {
            const response = await DoctorService.getByAdminsClinic()
            setDoctors(response.data)
        } catch (error) {
            console.error(`Error loading doctors !: ${error}`);
        }
    }


    async function fetchNurses() {
        try {
            const response = await NurseService.getByAdminsClinic()
            setNurses(response.data)
        } catch (error) {
            console.error(`Error loading nurses !: ${error}`);
        }
    }

    async function addAppointment(appointment) {
        try {
            await AppointmentService.create(appointment)
            alert("Successfully created an appointment!")
            history.push("/")
        }
        catch (error) {

            if(error.response.status == 400) {
                alert("Entered data is invalid. Date/time you're trying to take might already be taken.")
                setError("Entered data is invalid. Date/time you're trying to take might already be taken.")
            }
            console.error(`Error while adding new appointment: ${error}`);
        }
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
        console.log('NURSE:' + JSON.parse(nurse))

        console.log('doct id: ' + JSON.parse(doctor).id);
        console.log('NURSE ID:' + JSON.parse(nurse).id)

        if(valid(date,start,end,price)) {

            let appointment = {
                "date": date, "start": start, "end": end,
                "doctor": {"id": JSON.parse(doctor).id}, "nurse": {"id": JSON.parse(nurse).id},
                "patient": null,
                "price": price,
                "status": 'FREE'
            };
            console.log(appointment);
            console.log(JSON.stringify(appointment));
            addAppointment(appointment);



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
           <p style={{textAlign:'center', margin:'20px', color:'indianred'}}> {error} </p>
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
                            <option key={doctor.id} value={JSON.stringify(doctor)}>{doctor.user.name + " " + doctor.user.lastName}</option>
                        )}
                    </select>

                    <label htmlFor="nurse" className="label-register">Nurse:</label>
                    <select name="nurse" id="nurse" className="input-register"
                    style={{marginLeft:'50px'}}>
                        {nurses.map((nurse) =>
                            <option key={nurse.id} value={JSON.stringify(nurse)}>{nurse.user.name + " " + nurse.user.lastName}</option>
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



