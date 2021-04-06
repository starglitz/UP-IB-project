import React, {useEffect, useState} from 'react';
import MUIDataTable from "mui-datatables";

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
        },
        {
            label: 'LBO',
            name: 'lbo',
            options: {
                filter: false,
                sort: true,
            }
        }]

    console.log(requests)

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
                displayRowCheckbox={false}
                displaySelectAll={false}
                adjustForCheckbox={false}
            />
        </>
    );
}