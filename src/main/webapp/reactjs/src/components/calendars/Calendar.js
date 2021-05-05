import * as React from 'react';
import { ScheduleComponent, Day, Week, Month, Year, Inject, ViewsDirective, ViewDirective } from '@syncfusion/ej2-react-schedule';
import {useEffect, useState} from "react";

export function Calendar() {

    const [appointments, setAppointments] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            .then(res => setAppointments(res))
            .catch(err => setError(err));
    },[])

    async function fetchData() {
        const res = await fetch('http://localhost:8080/allAppointments');
        return res.json()
    }

    const data = []
    let i = 1

    appointments.map((appointment) => {
        const datePieces = appointment.date.split("-");
        const startPieces = appointment.start.split(":");
        const endPieces = appointment.end.split(":");

        let calendarAppointment =
            {
                Id: i,
                Subject: "Nurse: " + appointment.nurse.name + "<br> Doctor: " + appointment.doctor.name,
                Description: "Price: " + appointment.price,
                StartTime: new Date(datePieces[0], datePieces[1] - 1, datePieces[2], startPieces[0], startPieces[1]),
                EndTime: new Date(datePieces[0], datePieces[1] - 1, datePieces[2], endPieces[0], endPieces[1]),
                IsAllDay: false
            }

        data.push(calendarAppointment)
        i++
    })

    console.log(data)

    return (
        <ScheduleComponent height='550px' eventSettings={{ dataSource: data }}>
            <ViewsDirective>
                <ViewDirective option='Week'/>
                <ViewDirective option='Month'/>
                <ViewDirective option='Year'/>
            </ViewsDirective>
            <Inject services={[Week, Month, Year]}/>
        </ScheduleComponent>
    )
}