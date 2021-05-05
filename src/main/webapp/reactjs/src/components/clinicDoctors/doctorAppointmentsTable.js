import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Typography} from "@material-ui/core";
import MUIDataTable from "mui-datatables";

function DoctorAppointmentsTable({appointments}) {

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
        selectableRows: 'none',
        viewColumns: false

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