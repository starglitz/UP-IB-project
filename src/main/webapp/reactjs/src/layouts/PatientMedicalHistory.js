import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {PatientTable} from '../components/tables/PatientTable'
import {Calendar} from '../components/calendars/Calendar'
import 'react-calendar/dist/Calendar.css';
import AppointmentsTable from "../components/tables/AppointmentsTable";
import {PatientIllnessTable} from "../components/patients/PatientIllnessTable";

const PatientMedicalHistory = () => {


    return (
        <>
            <div className="content-box">
                <PatientIllnessTable />
            </div>
        </>
    );
}
export default PatientMedicalHistory