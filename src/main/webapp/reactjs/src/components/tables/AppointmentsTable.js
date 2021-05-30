import React, {useEffect, useState} from "react";
import RegisterRequestRow from "../registerRequestRow";
import AppointmentRow from "../AppointmentRow";
import {useHistory} from "react-router-dom";
import {AppointmentService} from "../../services/AppointmentService";

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
        try {
            const response = await AppointmentService.getAll()
            setAppointments(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }



    let delete_appointment = (appointment_id) => {
        console.log("delet appointment clicked")
        let appointment = {deleted:true, appointment_id:appointment_id};
        setAppointments(appointments.filter(app => app.deleted === false))
        console.log(appointment_id)
        sendDataDelete(appointment_id);
    }
    const update_appointment = (appointment_id) => {
        history.push({
            pathname: '/updateAppointment',
            search: '?id=' + appointment_id,
            state: { detail: appointment_id}
        });
        setRandom(Math.random())
    };




    async function sendDataDelete(appointment_id){

            try {
                await AppointmentService.deleteAppointment(appointment_id)
                setAppointments((appointments) => appointments.filter((appointment) => appointment.id !== appointment_id))
                setRandom(Math.random())
            } catch (error) {
                console.error(`Error deleting appointment! : ${error}`);
            }

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
                      duration={app.duration} id={app.appointment_id} doctor={app.doctor.user}
                      nurse={app.nurse.user} price={app.price} deleted={app.deleted}/>

                )}
                </tbody>
            </table>
        </>
    );
};
// filter(app => app.deleted === false)
export default AppointmentsTable;