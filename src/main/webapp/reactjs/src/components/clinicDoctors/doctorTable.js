import React, {useEffect, useState} from 'react';
import MUIDataTable from "mui-datatables";
import {useParams} from "react-router-dom";
import DoctorAppointmentsTable from "./doctorAppointmentsTable";
import {AppointmentService} from "../../services/AppointmentService";
import {DoctorService} from "../../services/DoctorService";
import {Typography} from "@material-ui/core";

function DoctorTable() {
    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)
    const [appointments, setAppointments] = useState([])


    useEffect(() => {
        fetchData()
            // .then(res => setRequests(res))
            // .catch(err => setError(err));
    }, [])



    const {clinicId} = useParams();
    const {date} = useParams();

    // async function fetchData() {
    //     const res = await fetch('http://localhost:8080/doctors/' + clinicId + "/" + date,);
    //     return res.json()
    // }


    async function fetchData() {
        try {
            const response = await DoctorService.getByClinicAndDate(clinicId, date)
            setRequests(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }

    async function fetchDataAppointments(doctor_id, date) {
        try {
            const response = await AppointmentService.getFreeByDoctorIdAndDate(doctor_id, date)
            setAppointments(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }


    const clickHandler = (e) => {

        // async function fetchDataAppointments() {
        //     const res = await fetch('http://localhost:8080/doctorAppointments/' + e[0],);
        //     return res.json()
        // }
        //
        // fetchDataAppointments()
        //     .then(res => setAppointments(res))
        //     .catch(err => setError(err));
        fetchDataAppointments(e[0], date)
    }

console.log(requests)


    const columns = [
        {
            label: 'ID',
            name: 'id',
            options: {
                display: false,
                filter: false,
                sort: true,
            }
        },
        {
            label: 'Name',
            name: 'user',
            options: {
                filter: true,
                sort: true,
                customBodyRender: (value, tableMeta, updateValue) => (
                    <Typography>
                        {value.name}
                    </Typography>
                )
            }
        },
        {
            label: 'Lastname',
            name: 'user',
            options: {
                filter: true,
                sort: true,
                customBodyRender: (value, tableMeta, updateValue) => (
                    <Typography>
                        {value.lastName}
                    </Typography>
                )
            }
        },
        {
            label: 'Grade',
            name: 'grade',
            options: {
                filter: true,
                sort: true,
            }
        }]

    const options = {
        selectableRows: 'none',
        viewColumns: false,
        onRowClick: clickHandler
    };

    return (
        <>
            <div id="mojaTabela">
                <MUIDataTable
                    title={"Doctors List"}
                    data={requests}
                    columns={columns}
                    options={options}
                />
            <DoctorAppointmentsTable appointments={appointments}/>
            </div>
        </>
    );
}

export default DoctorTable;