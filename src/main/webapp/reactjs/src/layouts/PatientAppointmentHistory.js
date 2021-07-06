import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {AppointmentService} from "../services/AppointmentService";
import {Typography} from "@material-ui/core";
import MUIDataTable from "mui-datatables";

function PatientAppointmentHistory() {

    const [requests, setRequests] = useState([])

    useEffect(() => {
        fetchData()

    },[])



    async function fetchData() {
        try {
            const response = await AppointmentService.getPatientAppointmentsHistory();
            setRequests(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }




    const columns = [
        {
            label: 'id',
            name: 'appointment_id',
            options: {
                display: false,
                filter: false
            }
        },
        {
            label: 'Doctor',
            name: 'doctor',
            options: {
                filter: true,
                sort: true,
                customBodyRender: (value, tableMeta, updateValue) => (
                    <Typography>
                        {value.user.name + " " + value.user.lastName}
                    </Typography>
                )
            }
        },
        {
            label: 'Date',
            name: 'date',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Start at',
            name: 'start',
            options: {
                filter: true,
                sort: false,
            }
        },
        {
            label: 'Price',
            name: 'price',
            options: {
                filter: true,
                sort: false,
            }
        }
    ]

    const options = {
        selectableRows: 'none',
        viewColumns: false

    };

    return (
        <>
            <div id="mojaTabela">
                <MUIDataTable
                    title={"Appointment History List"}
                    data={requests}
                    columns={columns}
                    options={options}
                />
            </div>
        </>
    );
}

export default PatientAppointmentHistory;