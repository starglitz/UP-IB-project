import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import MUIDataTable from "mui-datatables";
import {Typography} from "@material-ui/core";

function AppointmentTable() {


    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            .then(res => setRequests(res))
            .catch(err => setError(err));
    },[])

    const {id} = useParams();

    async function fetchData() {
        const res = await fetch('http://localhost:8080/clinicAppointments/' + id);
        return res.json()
    }

    const clickHandler = (e) => {


    }

    console.log(requests)


    const columns = [
        {
            label: 'id',
            name: 'id',
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
                        {value.name}
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
            label: 'Duration',
            name: 'duration',
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
        selectableRows: false,
        onRowClick: clickHandler,
        viewColumns: false

    };

    return (
        <>
            <div id="mojaTabela">
                <MUIDataTable
                    title={"Appointment List"}
                    data={requests}
                    columns={columns}
                    options={options}
                />
            </div>
        </>
    );
}

export default AppointmentTable;