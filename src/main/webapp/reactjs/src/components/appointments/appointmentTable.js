import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import MUIDataTable from "mui-datatables";
import {Typography} from "@material-ui/core";
import {useHistory} from "react-router-dom";
import {AppointmentService} from "../../services/AppointmentService";
function AppointmentTable() {


    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)
    const history = useHistory();

    useEffect(() => {
        fetchData()

    },[])

    const {id} = useParams();

    // async function fetchData() {
    //     const res = await fetch('http://localhost:8080/clinicAppointments/' + id);
    //     return res.json()
    // }


    async function fetchData() {
        try {
            const response = await AppointmentService.getFreeByClinicId(id);
            setRequests(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }


    const clickHandler = (e) => {
        const id = e[0];
        history.push("/booking/" + id)
    }

    console.log(requests)


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