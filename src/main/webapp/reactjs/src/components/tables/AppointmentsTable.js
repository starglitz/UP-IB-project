import React, {useEffect, useState} from "react";
import RegisterRequestRow from "../registerRequestRow";
import AppointmentRow from "../AppointmentRow";
import {useHistory} from "react-router-dom";

const AppointmentsTable = () => {

    const [appointments, setAppointments] = useState([]);
    const [hasError, setErrors] =  useState(false);

    const [random, setRandom] = useState(Math.random());

    let history = useHistory();

    useEffect(() => {
        fetchData();
        console.log(appointments)
    }, [random]); // [] as second argument makes it load only once

    async function fetchData() {
        const res = await fetch("http://localhost:8080/allAppointments");
        res
            .json()
            .then(res => res.filter(app => app.deleted === false))
            .then(res => setAppointments(res))
            .catch(err => setErrors(err));
    }




    let delete_appointment = (appointment_id) => {
        console.log("delet appointment clicked")
        let appointment = {deleted:true, appointment_id:appointment_id};
        setAppointments(appointments.filter(app => app.deleted === false))
        setRandom(Math.random())
        sendDataDelete(appointment);
    }
    const update_appointment = (appointment_id) => {
        history.push({
            pathname: '/updateAppointment',
            search: '?id=' + appointment_id,
            state: { detail: appointment_id}
        });
    };


    // let decline = (register_request_id) => {
    //     let request = {status:"DECLINED", register_request_id:register_request_id};
    //     setRequests(requests.filter(req => req.status === "PENDING"))
    //     setRandom(Math.random())
    //    // sendData(request);
    // }

    const sendDataDelete = (data) => {
        fetch('http://localhost:8080/deleteAppointment', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => response)
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    const reRender = () => setRandom(Math.random());

    return (
        <>
            <table className="requestsTable appointmentsTable">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Duration</th>
                    <th>Doctor</th>
                    <th>Nurse</th>
                    <th>Price</th>
                    <th colSpan="2">Delete/Modify an appointment</th>
                </tr>

                {appointments.filter(app => app.deleted === false).map((app) =>
                    <AppointmentRow deleteAppointment={delete_appointment} updateAppointment={update_appointment}
                      status={app.status} key={app.appointment_id} date={app.date} start={app.start} end={app.end}
                      duration={app.duration} id={app.appointment_id} doctor={app.doctor}
                      nurse={app.nurse} price={app.price} deleted={app.deleted}/>

                )}
                </tbody>
            </table>
        </>
    );
};
// filter(app => app.deleted === false)
export default AppointmentsTable;