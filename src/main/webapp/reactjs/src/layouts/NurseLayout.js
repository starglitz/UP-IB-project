import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {PatientTable} from '../components/tables/PatientTable'
import {Calendar} from '../components/calendars/Calendar'
import 'react-calendar/dist/Calendar.css';

const NurseLayout = () => {

    return (
        <>
            <h3 style={{textAlign: 'center', margin: '30px'}}>Welcome nurse MARIJA</h3>

            <div className="content-box">
                <Calendar />
                <hr/>
                <PatientTable />
            </div>
        </>
    );
}
export default NurseLayout