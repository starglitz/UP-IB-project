import * as React from 'react';
import { ScheduleComponent, Day, Week, Month, Year, Inject, ViewsDirective, ViewDirective } from '@syncfusion/ej2-react-schedule';
import {useEffect, useState} from "react";
import {AppointmentService} from "../../services/AppointmentService";
import {TokenService} from "../../services/TokenService";
import {UserService} from "../../services/UserService";
import {RecipeService} from "../../services/RecipeService";
import App from "../../App";

export function Calendar() {

    const [appointments, setAppointments] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData().catch(err => setError(err));
    },[])

    async function fetchData() {
        try {

            const decoded_token = TokenService.decodeToken(TokenService.getToken().sub());
            const user = await UserService.getByEmail(decoded_token.sub)

            console.log(user.data)
            console.log(decoded_token.roles)
            console.log(decoded_token.roles)
            console.log(decoded_token.roles)

            if (decoded_token.roles === "[NURSE]") {
                const response = await AppointmentService.getNurseAppointments(user.data.id)
                console.log(response)
                setAppointments(response.data)
            }
            if (decoded_token.roles === "[DOCTOR]") {
                const response = await AppointmentService.getDoctorAppointments(user.data.id)
                console.log(response)
                setAppointments(response.data)
            }
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
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
                Subject: "Nurse: " + appointment.nurse.user.name + "<br> Doctor: " + appointment.doctor.user.name,
                Description: "Price: " + appointment.price + " ---- Status: " + appointment.status,
                StartTime: new Date(datePieces[0], datePieces[1] - 1, datePieces[2], startPieces[0], startPieces[1]),
                EndTime: new Date(datePieces[0], datePieces[1] - 1, datePieces[2], endPieces[0], endPieces[1]),
                IsAllDay: false
            }

        data.push(calendarAppointment)
        i++
    })

    console.log(appointments)
    console.log(data)

    return (
        <div>
            <ScheduleComponent height='550px' eventSettings={{ dataSource: data }}>
                <ViewsDirective>
                    <ViewDirective option='Week'/>
                    <ViewDirective option='Month'/>
                    <ViewDirective option='Year'/>
                </ViewsDirective>
                <Inject services={[Week, Month, Year]}/>
            </ScheduleComponent>
        </div>
    )
}
