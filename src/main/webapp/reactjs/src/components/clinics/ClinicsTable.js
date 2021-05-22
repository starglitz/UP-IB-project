import React, {useEffect, useState} from 'react';
import MUIDataTable, { TableFilterList }  from "mui-datatables";
import {useHistory} from "react-router-dom";
import {Tooltip} from "@material-ui/core";
import TextField from '@material-ui/core/TextField';
import {AppointmentService} from "../../services/AppointmentService";
import {ClinicService} from "../../services/ClinicService";

function ClinicsTable() {

    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
    }


    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)
    const [date, setDate] = useState(formatDate('Sun Jan 01,2000'));
    const [filter, setFilter] = useState(false);
    const history = useHistory();


    useEffect(() => {
        // fetchData("http://localhost:8080/clinics/" + date)
        //     .then(res => setRequests(res))
        //     .catch(err => setError(err));
        fetchData()
    },[date])
console.log(requests)

    async function fetchData() {
        try {
            const response = await ClinicService.getByDate(date);
            setRequests(response.data)
        } catch (error) {
            console.error(`Error loading clinics !: ${error}`);
            setRequests([])
        }
    }


    // async function fetchData(url) {
    //     const res = await fetch(url,);
    //     return res.json()
    // }

    const listenerHandler = (e) => {
        if(!filter){
            clickHandler(e)
        }
        else {
            clickHandler2(e)
        }
    }

    const clickHandler = (e) => {
        const id = e[0];
        history.push("/clinic/" + id);
    }

    const datePickHandler = (e) => {
       setFilter(true);
       setDate(e);
    }

    const clickHandler2 = (e) => {
        const id = e[0];
        history.push("/doctors/" + id + "/" + date)
    }
    const columns = [
        {
            label: 'id',
            name: 'clinic_id',
            options: {
                display:false,
                filter: false
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
            label: 'Descriptionn',
            name: 'description',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Rating',
            name: 'rating',
            options: {
                filter: true,
                sort: false,
            }
        }]

    const AddButton = () => (
        <Tooltip disableFocusListener title="Pick Date">
            <TextField
                id="date"
                label="Appointment Date"
                type="date"
                InputLabelProps={{
                    shrink: true,
                }}
                onChange={(event) => datePickHandler(event.target.value)}
            />
        </Tooltip>
    );


    const options = {
        selectableRows: "none",
        onRowClick: listenerHandler,
        viewColumns: false,
        customToolbar: AddButton

    };

    return (
        <>
            <div id="mojaTabela">
            <MUIDataTable
                title={"Clinic List"}
                data={requests}
                columns={columns}
                options={options}
            />
            </div>
        </>
    );
}

export default ClinicsTable;