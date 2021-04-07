import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {PatientTable} from '../components/tables/PatientTable'
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import {useHistory} from 'react-router-dom'

const NurseLayout = () => {
    const history = useHistory();

    const [value, onChange] = useState(new Date());

    const dayClick = (value, event) => {
        history.push({
            pathname: "/recipes",
            state: {searchDate: value}
        })
    }

    return (
        <>
            <h3 style={{textAlign: 'center', margin: '30px'}}>Welcome nurse MARIJA</h3>

            <div className="content-box">
                <Calendar
                    onChange={onChange}
                    value={value}
                    onClickDay={dayClick}/>
                <hr/>
                <PatientTable />
            </div>
        </>
    );
}
export default NurseLayout