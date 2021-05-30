import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'react-calendar/dist/Calendar.css';
import ClinicsTable from "../components/clinics/ClinicsTable";
import NewClinic from "../components/clinics/NewClinic";

const ClinicCentreAdminLayout = () => {

    return (
        <>
            <div className="hospitalProfile-container">
                <NewClinic />
                <ClinicsTable />
            </div>
        </>
    );
}
export default ClinicCentreAdminLayout