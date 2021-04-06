import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {DataTable} from '../components/tables/DataTable'
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

const RegisterLayout = () => {


    const [value, onChange] = useState(new Date());
    return (
        <>
            <h1 style={{textAlign:'center', margin:'30px'}}>Welcome nurse MARIJA</h1>
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center'
            }}>

            </div>

            <div class="content-box">
                <Calendar
                    onChange={onChange}
                    value={value} />
                <hr/>
                <h1>Patient list</h1>
                <DataTable />
            </div>
        </>
    );
};

export default RegisterLayout;