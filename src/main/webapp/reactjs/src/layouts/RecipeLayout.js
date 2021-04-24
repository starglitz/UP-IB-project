import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'react-calendar/dist/Calendar.css';
import {RecipeTable} from '../components/tables/RecipeTable'
import Calendar from "react-calendar";
import {PatientTable} from "../components/tables/PatientTable";

const RecipeLayout = (searchDate) => {

    return (
        <>
            <div className="content-box">
                <RecipeTable />
            </div>
        </>
    )
}
export default RecipeLayout;