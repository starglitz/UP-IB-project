import * as React from 'react';
import {useEffect, useState} from "react";
import Button from "@material-ui/core/Button";
import {Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@material-ui/core";

function AppointmentTable(appointmentParam) { // trebalo bi da se pasuje appointment ovde

    const [appointment, setAppointment] = useState([]) // test state, ovo treba da se izbrise kada se napravi pretraga
    const [hasError, setError] = useState(false)
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        const recipe = document.getElementById("description").value
        document.getElementById("recipeList").value += recipe + '\n'
    };

    useEffect(() => {
        fetchAppointment()
            .then(res => setAppointment(res))
            .catch(err => setError(err));
    },[])

    async function fetchAppointment() { // test fetch, umesto ovoga se passuje appoinntment iz pretrage
        const res = await fetch("http://localhost:8080/appointment/17");
        return res.json()
    }


    async function postAppointment() {
        fetch('http://localhost:8080/updateAppointment/' + appointment.appointment_id, {
            mode: 'no-cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(appointment),
        })
            .then(response => response.json())
            .then(appointment => {
                console.log('Success:', appointment);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    const handleFinish = () => {
        const recipes = document.getElementById("recipeList").value.split("\n")
        Object.keys(recipes).forEach(function (key) {

            let recipe = {
                "recipe_id": Math.floor(Math.random() * 100000),
                "description": recipes[key].trim(),
                "issueDate": appointment.date,
                "nurse": appointment.nurse,
                "validated": false,
                "patientBookId": appointment.patient.patientBookId
            };

            if (recipe.description !== "") {
                console.log(recipe)
                fetch('http://localhost:8080/addRecipe', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(recipe),
                })
                    .then(response => response.json())
                    .then(recipe => {
                        console.log('Success:', recipe);
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            }
        })
        document.getElementById("recipeList").value = ""
    }

    return (
    <>
        <div className="hospitalProfile-container">

            <h1>Appointment in progress</h1>

            <ul>

                {/*<li className="info-row">*/}
                {/*    <p>*/}
                {/*        {appointment.patient.name + " " +*/}
                {/*        appointment.patient.lastName + " / " +*/}
                {/*        appointment.patient.address + " / " +*/}
                {/*        appointment.patient.city}*/}
                {/*    </p>*/}
                {/*</li>*/}

                <li className="info-row">


                    <Button variant="outlined" color="primary" onClick={handleClickOpen}>
                        Add recipe
                    </Button>
                    <Dialog maxWidth open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">
                            Give recipe to the patient
                        </DialogTitle>
                        <DialogContent>
                            <TextField
                                autoFocus
                                margin="dense"
                                id="description"
                                label="Description"
                                type="text"
                                fullWidth
                            />
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={handleClose} color="primary">
                                Cancel
                            </Button>
                            <Button onClick={handleClose} color="primary">
                                Continue
                            </Button>
                        </DialogActions>
                    </Dialog>

                    <textarea id="recipeList" className="form-control" disabled/>
                </li>

                    <input type="button" className="form-control" value="Finish appointment" color="primary" onClick={() => {handleFinish()}} />
            </ul>

        </div>
    </>
    );
}

export default AppointmentTable