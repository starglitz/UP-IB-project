import React, {useEffect, useState} from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";
import {classes} from "istanbul-lib-coverage";
import Button from "@material-ui/core/Button";
import MUIDataTable from "mui-datatables";
import {PatientService} from "../../services/PatientService";
import {AppointmentService} from "../../services/AppointmentService";
import {useHistory} from "react-router-dom";

const PatientAppointments = () => {


    const [appointments, setAppointments] = useState([])
    const [hasError, setError] = useState(false)
    const [filter, setFilter] = useState(false);
    const history = useHistory();
    const [requests, setRequests] = useState([])

    useEffect(() => {
        fetchData()
            .catch(err => setError(err));
    },[])

    async function fetchData() {
        try {
            console.log(localStorage.getItem("PATIENT_ID"))
            const response = await AppointmentService.getPatientAppointments(localStorage.getItem("PATIENT_ID"))
            const appointments = []
            for (let i = 0; i < response.data.length; i++) {
                appointments.push(response.data[i]);
            }
            console.log(appointments)
            localStorage.setItem("DOCTOR_ID", appointments[0].doctor.id)
            setRequests(appointments)
        } catch (error) {
            console.error(`Error loading patients !: ${error}`);
        }
    }

    const columns = [
        {
            label: 'ID',
            name: 'appointment_id',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Date',
            name: 'date',
            options: {
                filter: true,
                sort: false,
            }
        },
        {
            label: 'Start',
            name: 'start',
            options: {
                filter: true,
                sort: false,
            }
        },
        {
            label: 'End',
            name: 'end',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Price',
            name: 'price',
            options: {
                filter: true,
                sort: true,
            }
        }]

    const listenerHandler = (e) => {
        const today = new Date();
        const time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
        const appointmentDate = e[1].split("-");
        const startPieces = e[2].split(":");
        const endPieces = e[3].split(":");
        if(!filter){
            const start = new Date(appointmentDate[0], appointmentDate[1] - 1, appointmentDate[2], startPieces[0], startPieces[1])
            const current = new Date(appointmentDate[0], appointmentDate[1] - 1, appointmentDate[2], today.getHours(), today.getMinutes())
            const end = new Date(appointmentDate[0], appointmentDate[1] - 1, appointmentDate[2], endPieces[0], endPieces[1])
            console.log("START " + start)
            console.log("CURRENT " + time)
            console.log("END " + end)

            if (current > start && current < end) {
                clickHandler(e)
            } else
                alert("The appointment you have chosen begins \n" + start)

        }
    }

    const clickHandler = (e) => {
        const id = e[0];
        localStorage.setItem("APPOINTMENT_ID", id)
        console.log(e)
        history.push({
            pathname: '/patient/appointment/review',
            search: '?id=' + id,
            state: { detail: id}
        });
    }

    const options = {
        selectableRows: "none",
        onRowClick: listenerHandler,
        viewColumns: false,
    };

    return (
        <div style={{margin: "2% 30% 5% 30%"}}>
            <MUIDataTable
                title={"Appointments for " + localStorage.getItem("PATIENT_NAME")}
                data={requests}
                columns={columns}
                options={options}
            />
        </div>
    );
};

export default PatientAppointments;