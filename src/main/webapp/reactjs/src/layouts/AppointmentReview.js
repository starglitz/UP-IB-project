import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'react-calendar/dist/Calendar.css';
import AppointmentReviewBox from "../components/appointments/AppointmentReviewBox";
import {PatientIllnessTable} from "../components/patients/PatientIllnessTable";

const AppointmentReview = () => {
    return (
            <>
                <AppointmentReviewBox />
                <PatientIllnessTable />
            </>
    );
};

export default AppointmentReview