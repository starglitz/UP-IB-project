import React, {useEffect, useState} from 'react';

import hospitalImg from "../assets/hospital-clinicProfile.png";
import {ClinicService} from "../services/ClinicService";
import Box from '@material-ui/core/Box';
import Rating from '@material-ui/lab/Rating';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import Typography from '@material-ui/core/Typography';
import DoctorsTable from "../components/businessReports/doctorsTable";
import {render} from "@testing-library/react";
import Chart from "../components/businessReports/chart";
import Charts from "../components/businessReports/chart";
import ChartWeeks from "../components/businessReports/chartWeeks";
import IncomeComponent from "../components/businessReports/incomeComponent";
import ChartDays from "../components/businessReports/chartDays";


function BussinesReports() {


    const [clinic, setClinic] = useState({

        clinic_id: 0,
        name: '',
        description: '',
        rating: 0,
        priceList: [],
        addressName:'',
        lat:'',
        lng:''
    });
    const [clinicId, setClinicId] = useState(0)
    const [loaded, setLoaded] = useState(false)

    useEffect(() => {
        fetchData()

    }, [loaded]);

    async function fetchData() {
        try {
            const response = await ClinicService.getByLoggedInAdmin();
            setClinic(response.data)
            setClinicId(clinic.clinic_id)
            setLoaded(true)

        } catch (error) {
            console.error(`Error loading clinic !: ${error}`);
        }
    }


    return (
        <>
            <div className="hospitalReports-container">

                <img src={hospitalImg}></img>
                <h1>Hospital business reports</h1>




                <ul>
                    <li className="rating-row">
                        <p>Rating:</p>
                        <Box component="fieldset" mb={3} borderColor="transparent">
                            <Rating name="read-only" value={clinic.rating} precision={0.1} readOnly />
                        </Box>
                    </li>

                </ul>


                <DoctorsTable clinic_id={clinicId}/>

                <Chart clinic_id={clinicId}/>

                <ChartWeeks clinic_id={clinicId}/>
                <IncomeComponent clinic_id={clinicId}/>
            </div>


        </>
    );
}

export default BussinesReports;