import React, {Component, useEffect, useState} from 'react';
import { DataGrid } from '@material-ui/data-grid';
import RegisterRequestRow from "../components/registerRequestRow";

const RegisterRequests = () => {

    const [requests, setRequests] = useState([]);
    const [hasError, setErrors] =  useState(false);

    useEffect(() => {
            setRequests([]);
            fetchData();
    }, []); // [] as second argument makes it load only once

    async function fetchData() {
        const res = await fetch("http://localhost:8080/allRegisteringRequests");
        res
            .json()
            .then(res => setRequests(res))
            .catch(err => setErrors(err));
    }



    const columns = [
        { field: 'register_request_id', headerName: 'Request ID', width: 130 },
        { field: 'patient', headerName: 'Patient', width: 130 },
        { field: 'status', headerName: 'Status', width: 130 }
    ];

console.log(requests);
    const rows = [requests];

console.log(rows);
    return (
        <>
           <table className="requestsTable">

               <tr>
                   <th>ID</th>
                   <th>Name & Surname</th>
                   <th>Status</th>
                   <th colspan="2">Accept or decline a request</th>
               </tr>

                <tbody>
                   {requests.map((req) => (
                       // <tr key={req.id}>
                       // <td key={req.id}>{req.id}</td>
                       // <td key={req.id}>{req.email}</td>
                       // <td key={req.id}>{req.name}</td>
                       // </tr>
                       //  <p key={req.id}>{req.name}</p>
                       <RegisterRequestRow key={req.id} email={req.patient.email} id={req.id} name={req.patient.name} patientid={req.patient.id}/>

                   ))}
                </tbody>

           </table>
            {/*<DataGrid rows={rows} columns={columns} pageSize={5} checkboxSelection />*/}
            {/*<span>{JSON.stringify(requests)}</span>*/}
        </>
    );
};

export default RegisterRequests;