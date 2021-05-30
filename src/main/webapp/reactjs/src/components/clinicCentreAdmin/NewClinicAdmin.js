import React, {Component, useEffect, useState} from "react";
import {Redirect, useHistory} from "react-router-dom";
import {InputLabel, MenuItem, Select, TableBody, TableCell, TableRow, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {DoctorService} from "../../services/DoctorService";
import {ClinicService} from "../../services/ClinicService";

export default function NewClinicAdmin () {

    const [clinics, setClinics] = useState([])
    const [clinic, setClinic] = useState({})
    const [error, setError] = useState([])
    const history = useHistory();

    useEffect(() => {
        fetchClinics().catch(err => setError(err));
    }, []);

    async function fetchClinics() {
        try {
            const response = await ClinicService.getAll()
            setClinics(response.data)
        } catch (error) {
            console.error(`Error loading doctors !: ${error}`);
        }
    }

    function registerClinicAdmin () {
        let name = document.getElementById('name').value;
        let lastName = document.getElementById('lastName').value;
        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;
        let address = document.getElementById('address').value;
        let city = document.getElementById('city').value;
        let country = document.getElementById('address').value;
        let phone = document.getElementById('phone').value;

        const user = {
            'name': name,
            'lastName': lastName,
            'username': username,
            'password': password,
            'address': address,
            'city': city,
            'country': country,
            'phone': phone,
        }

        console.log(user)
    }

    function handleChange(event) {
        console.log(event.target.value)
        setClinic(event.target.value)
    }

    console.log(clinics)

    return (
        <div className="form-size">
            <h1 >New clinic admin</h1>

            <hr />

            <TextField fullWidth className="input-margin" label="Name" id="name" type="text" variant="outlined" />
            <br />
            <TextField fullWidth className="input-margin" label="Last name" id="lastName" type="text" variant="outlined" />
            <br />
            <TextField fullWidth className="input-margin" label="Username" id="username" type="text" variant="outlined" />
            <br />
            <TextField fullWidth className="input-margin" label="Password" id="password" type="password" variant="outlined" />
            <br />
            <TextField fullWidth className="input-margin" label="Address" id="address" type="text" variant="outlined" />
            <br />
            <TextField fullWidth className="input-margin" label="City" id="city" type="text" variant="outlined"/>
            <br />
            <TextField fullWidth className="input-margin" label="Country" id="country" type="text" variant="outlined"/>
            <br />
            <TextField fullWidth className="input-margin" label="Phone" id="phone" type="text" variant="outlined"/>
            <hr />

            <InputLabel id="clinicLabel">Clinic</InputLabel>
            <Select fullWidth
                labelId="clinicLabel"
                name="clinic"
                id="clinic"
                onChange={handleChange}
                variant="outlined"
            >
                {clinics.map(clinic => (
                    <MenuItem key={clinic.id} value={clinic}> {clinic.name} </MenuItem>
                ))}
            </Select>
            {/*<select name="clinic" id="clinic" className="input-register">*/}
            {/*    {clinics.map((clinic) =>*/}
            {/*        <option key={clinic.id} value={JSON.stringify(clinic)}>{clinic.name}</option>*/}
            {/*    )}*/}
            {/*</select>*/}
            <hr />
            <Button fullWidth size="large" color="inherit" onClick={() => registerClinicAdmin()}>
                Submit
            </Button>
        </div>
    );
}