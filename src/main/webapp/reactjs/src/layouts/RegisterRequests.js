import React, { useEffect, useState} from 'react';
import RegisterRequestRow from "../components/registerRequestRow";
import {useHistory} from "react-router-dom";


const RegisterRequests = () => {

    const [requests, setRequests] = useState([]);
    const [hasError, setErrors] =  useState(false);

    const [random, setRandom] = useState(Math.random());


    useEffect(() => {
            fetchData();
    }, [random]); // [] as second argument makes it load only once

    async function fetchData() {
        const res = await fetch("http://localhost:8080/allRegisteringRequests");
        res
            .json()
            .then(res => res.filter(req => req.status === "PENDING"))
            .then(res => setRequests(res))
            .catch(err => setErrors(err));
    }


    let accept = (register_request_id) => {
        let request = {status:"APPROVED", register_request_id:register_request_id};
        setRequests(requests.filter(req => req.status === "PENDING"))
        setRandom(Math.random())
        sendData(request);
    }

    let decline = (register_request_id) => {
        let request = {status:"DECLINED", register_request_id:register_request_id};
        setRequests(requests.filter(req => req.status === "PENDING"))
        setRandom(Math.random())
        sendData(request);
    }

    const sendData = (data) => {
        fetch('http://localhost:8080/updateRequest', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => response)
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    const reRender = () => setRandom(Math.random());

    return (
        <>
           <table className="requestsTable">
               <tbody>
                   <tr>
                       <th>ID</th>
                       <th>Name & Surname</th>
                       <th>Status</th>
                       <th colSpan="2">Accept or decline a request</th>
                   </tr>
                   {requests.filter(req => req.status === "PENDING").map((req) =>
                        <RegisterRequestRow accept={accept} decline={decline} status={req.status} key={req.register_request_id} email={req.patient.email} id={req.register_request_id} name={req.patient.name} patientid={req.patient.id}/>
                       )}
                </tbody>
           </table>
        </>
    );
};

export default RegisterRequests;