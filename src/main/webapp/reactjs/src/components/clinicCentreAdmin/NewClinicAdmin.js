import React, {Component, useEffect, useState} from "react";
import {Redirect, useHistory} from "react-router-dom";
import {InputLabel, MenuItem, Select, TableBody, TableCell, TableRow, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {ClinicService} from "../../services/ClinicService";
import {ClinicAdminService} from "../../services/ClinicAdminService";

export default function NewClinicAdmin () {

    const [clinics, setClinics] = useState([])
    const [clinic, setClinic] = useState({'name': ''})
    const [error, setError] = useState([])
    const history = useHistory();

    useEffect(() => {
        fetchClinics()
            .then(() => {
                let clinicMenuItem = document.getElementById('clinicMenuItem').value;
                clinicMenuItem.key = clinics[0].name
            })
            .catch(err => setError(err));
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
        let email = document.getElementById('email').value;
        let password = document.getElementById('password').value;
        let address = document.getElementById('address').value;
        let city = document.getElementById('city').value;
        let country = document.getElementById('address').value;
        let phone = document.getElementById('phone').value;

        const user = {
            'name': name,
            'lastName': lastName,
            'email': email,
            'password': password,
            'address': address,
            'city': city,
            'country': country,
            'phoneNumber': phone,
        }

        const clinicAdmin = {
            'clinic': clinic,
            'user': user
        }

        if (name === '' || lastName === '' || email === '' || password === '' || address === ''
            || city === '' || country === '' || phone === '' || clinic.name === '') {
            alert("input all fields !")
            return false
        }



        console.log(clinicAdmin)
        ClinicAdminService.create(clinicAdmin)
            .then(() => alert("Clinic admin successfully registered"))
        window.location.reload();
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
            <hr />

            <TextField fullWidth className="input-margin" label="Name" id="name" type="text" variant="outlined" />
            <br />
            <TextField fullWidth className="input-margin" label="Last name" id="lastName" type="text" variant="outlined" />
            <br />
            <TextField fullWidth className="input-margin" label="Email" id="email" type="email" variant="outlined" />
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
            <Button fullWidth size="large" color="inherit" onClick={() => registerClinicAdmin()}>
                Submit
            </Button>
        </div>
    );
}