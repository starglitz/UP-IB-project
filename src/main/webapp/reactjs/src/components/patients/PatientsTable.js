import React, {useEffect, useState} from 'react';
import MUIDataTable, { TableFilterList }  from "mui-datatables";
import {useHistory} from "react-router-dom";
import {Tooltip} from "@material-ui/core";
import TextField from '@material-ui/core/TextField';
import {AppointmentService} from "../../services/AppointmentService";
import {PatientService} from "../../services/PatientService";

const PatientsTable = () => {



    const [patients, setPatients] = useState([])
    const [hasError, setError] = useState(false)
    const [filter, setFilter] = useState(false);
    const history = useHistory();


    useEffect(() => {
        // fetchData("http://localhost:8080/allPatients")
        //     .then(res => setPatients(res))
        //     .catch(err => setError(err));
        fetchData()
    },[])


    async function fetchData() {
        try {
            const response = await PatientService.getAll()
            setPatients(response.data)
            let pat = response.data

            let list = []
            pat.forEach(patient => {
                list.push({approved: patient.approved,
                    enabled: patient.enabled,
                    lbo: patient.lbo,
                    patientBookId: patient.patientBookId,
                    address:patient.user.address,
                    city: patient.user.city,
                    country: patient.user.country,
                    email: patient.user.email,
                    id: patient.user.id,
                    lastName: patient.user.lastName,
                    lastPasswordResetDate: patient.user.lastPasswordResetDate,
                    name: patient.user.name,
                    phoneNumber: patient.user.phoneNumber,
                    });

            } )
            setPatients(list)
            console.log(list)
        } catch (error) {
            console.error(`Error loading patients !: ${error}`);
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
    }

    const clickHandler = (e) => {
        const id = e[0];
        history.push({
            pathname: '/patientProfile',
            search: '?id=' + id,
            state: { detail: id}
        });

    }


    const columns = [
        {
            label: 'id',
            name: 'id',
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
            label: 'Surname',
            name: 'lastName',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'LBO',
            name: 'lbo',
            options: {
                filter: true,
                sort: false,
            }
        }]




    const options = {
        selectableRows: "none",
        onRowClick: listenerHandler,
        viewColumns: false,
       // customToolbar: AddButton

    };

    return (
        <>
            <div id="mojaTabela">
                <MUIDataTable
                    title={"Patients List"}
                    data={patients}
                    columns={columns}
                    options={options}
                />
            </div>
        </>
    );
}

export default PatientsTable;