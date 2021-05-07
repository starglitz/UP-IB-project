import React, {useEffect, useState} from 'react';
import MUIDataTable from "mui-datatables";
import {useParams} from "react-router-dom";
import DoctorAppointmentsTable from "./doctorAppointmentsTable";

function DoctorTable() {
    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)
    const [appointments, setAppointments] = useState([])


    useEffect(() => {
        fetchData()
            .then(res => setRequests(res))
            .catch(err => setError(err));
    }, [])



    const {clinicId} = useParams();
    const {date} = useParams();

    async function fetchData() {
        const res = await fetch('http://localhost:8080/doctors/' + clinicId + "/" + date,);
        return res.json()
    }

    const clickHandler = (e) => {

        async function fetchDataAppointments() {
            const res = await fetch('http://localhost:8080/doctorAppointments/' + e[0],);
            return res.json()
        }

        fetchDataAppointments()
            .then(res => setAppointments(res))
            .catch(err => setError(err));
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
            name: 'name',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Lastname',
            name: 'lastName',
            options: {
                filter: true,
                sort: true,
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