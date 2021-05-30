import React, { useEffect, useState} from 'react';
import RegisterRequestRow from "../components/registerRequestRow";
import {useHistory} from "react-router-dom";
import {AppointmentService} from "../services/AppointmentService";
import {RegisterRequestService} from "../services/RegisterRequestService";


const RegisterRequests = () => {

    const [requests, setRequests] = useState([]);
    const [hasError, setErrors] =  useState(false);

    const [random, setRandom] = useState(Math.random());


    useEffect(() => {
            fetchData();
    }, [random]); // [] as second argument makes it load only once

    // async function fetchData() {
    //     const res = await fetch("http://localhost:8080/allRegisteringRequests");
    //     res
    //         .json()
    //         .then(res => res.filter(req => req.status === "PENDING"))
    //         .then(res => setRequests(res))
    //         .catch(err => setErrors(err));
    // }

    async function fetchData() {
        try {
            const response = await RegisterRequestService.getAll()
            setRequests(response.data.filter(req => req.status === "PENDING"))
            console.log(response.data.filter(req => req.status === "PENDING"))
        } catch (error) {
            console.error(`Error loading requests !: ${error}`);
        }
    }


    let accept = (register_request_id, patientid) => {
        let request = {status:"APPROVED", register_request_id:register_request_id, patient: {id:patientid}};
        setRandom(Math.random())
        console.log(register_request_id)
        sendData(register_request_id, request);
    }

    let decline = (register_request_id, patientid) => {
        let request = {status:"DECLINED", register_request_id:register_request_id, patient: {id:patientid}};
        setRandom(Math.random())
        console.log(register_request_id)
        sendData(register_request_id, request);
    }

   async function sendData(register_request_id, request){
        try {
            console.log("id: " + register_request_id)
            console.log("req: " + request)
            await RegisterRequestService.update(register_request_id, request)
            setRandom(Math.random())
        }
        catch(error) {
            console.log(error)
        }

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
                        <RegisterRequestRow accept={accept} decline={decline} name={req.patient.name} status={req.status} key={req.register_request_id} email={req.patient.email} id={req.register_request_id}  patientid={req.patient.id}/>
                       )}
                </tbody>
           </table>
        </>
    );
};

export default RegisterRequests;