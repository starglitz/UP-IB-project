import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {PatientTable} from '../components/tables/PatientTable'
import {Calendar} from '../components/calendars/Calendar'
import 'react-calendar/dist/Calendar.css';

const NurseLayout = () => {

    return (
        <>
            <div className="content-box">
                <Calendar />
                <hr/>
                <PatientTable />
            </div>
        </>
    );
}
export default NurseLayout