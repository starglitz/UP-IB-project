import React, {useEffect, useState} from 'react';
import MUIDataTable from "mui-datatables";
import {AppointmentService} from "../../services/AppointmentService";
import {PatientService} from "../../services/PatientService";

export const PatientTable = () => {

    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            .catch(err => setError(err));
    },[])

    // async function fetchData() {
    //     const res = await fetch('http://localhost:8080/allPatients',);
    //     return res.json()
    // }

    async function fetchData() {
        try {
            const response = await PatientService.getAll()
            const patients = []
            for (let i = 0; i < response.data.length; i++) {
                patients.push(response.data[i].user);
            }
            setRequests(patients)
        } catch (error) {
            console.error(`Error loading patients !: ${error}`);
        }
    }

    console.log(requests)

    const columns = [
        {
            label: 'ID',
            name: 'id',
            options: {
                filter: true,
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
            label: 'Last Name',
            name: 'lastName',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Email',
            name: 'email',
            options: {
                filter: true,
                sort: false,
            }
        },
        {
            label: 'Phone',
            name: 'phoneNumber',
            options: {
                filter: true,
                sort: false,
            }
        },
        {
            label: 'Address',
            name: 'address',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'City',
            name: 'city',
            options: {
                filter: true,
                sort: true,
            }
        }]

    const options = {
        selectableRows: false
    };

    return (
        <>
            <MUIDataTable
                title={"Patient List"}
                data={requests}
                columns={columns}
                options={options}
            />
        </>
    );
}