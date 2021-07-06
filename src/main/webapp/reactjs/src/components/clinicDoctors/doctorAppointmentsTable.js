import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Typography} from "@material-ui/core";
import MUIDataTable from "mui-datatables";
import {useHistory} from "react-router-dom";
function DoctorAppointmentsTable({appointments}) {

    const history = useHistory();


    const clickHandler = (e) => {

        const id = e[0];
        history.push("/booking/" + id)
    }
    console.log(appointments)

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
        viewColumns: false,
        onRowClick: clickHandler

    };

    return (
        <>
            <div id="mojaTabela">
                <MUIDataTable
                    title={"Appointment List"}
                    data={appointments}
                    columns={columns}
                    options={options}
                />
            </div>
        </>
    );
}

export default DoctorAppointmentsTable;