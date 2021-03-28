import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {SortingTable} from '../components/sortingTable/SortingTable.js'
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

            <div className="content-box">
                <SortingTable />
            </div>

            <div class="content-box">
                <Calendar
                    onChange={onChange}
                    value={value} />
            </div>
        </>
    );
};

export default RegisterLayout;