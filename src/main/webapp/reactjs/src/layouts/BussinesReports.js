import React, {useEffect, useState} from 'react';

import hospitalImg from "../assets/hospital-clinicProfile.png";
import {ClinicService} from "../services/ClinicService";
import Box from '@material-ui/core/Box';
import Rating from '@material-ui/lab/Rating';
import StarBorderIcon from '@material-ui/icons/StarBorder';
import Typography from '@material-ui/core/Typography';


function BussinesReports() {


    const [clinic, setClinic] = useState({

        clinic_id: 0,
        name: '',
        description: '',
        rating: '',
        priceList: [],
        addressName:'',
        lat:'',
        lng:''
    });


    useEffect(() => {
        fetchData()

    }, []);

    async function fetchData() {
        try {
            const response = await ClinicService.getByLoggedInAdmin();
            setClinic(response.data)
            console.log(response.data)

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


            </div>
        </>
    );
}

export default BussinesReports;