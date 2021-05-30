import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'react-calendar/dist/Calendar.css';
import {RecipeTable} from '../components/tables/RecipeTable'

const RecipeLayout = () => {

    return (
        <>
            <div className="content-box">
                <RecipeTable />
            </div>
        </>
    )
}
export default RecipeLayout;