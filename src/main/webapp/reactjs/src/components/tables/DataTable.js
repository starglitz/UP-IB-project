import React, {useEffect, useState} from 'react';
import { MDBDataTable  } from 'mdbreact';

export const DataTable = () => {

    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            .then(res => setRequests(res))
            .catch(err => setError(err));
    },[])

    async function fetchData() {
        const res = await fetch('http://localhost:8080/patients');
        return res.json()
    }

    const columnsData = [
        {
            label: 'ID',
            field: 'id',
        },
        {
            label: 'Name',
            field: 'name',
        },
        {
            label: 'Last Name',
            field: 'lastName',
        },
        {
            label: 'Email',
            field: 'email',
        },
        {
            label: 'Phone',
            field: 'phoneNumber',
        },
        {
            label: 'Address',
            field: 'address',
        },
        {
            label: 'City',
            field: 'city',
        },
        {
            label: 'LBO',
            field: 'lbo',
        }]

    return (
        <MDBDataTable
            bordered={true}
            striped={true}
            dark={true}
            theadTextWhite={true}
            tbodyTextWhite={true}
            data={{
                columns: columnsData,
                rows: requests
            }}
            sortable={true}
        />
    );
}