import * as React from 'react';
import {useEffect, useState} from "react";
import Button from "@material-ui/core/Button";
import {Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@material-ui/core";
import {AppointmentService} from "../../services/AppointmentService";
import {RecipeService} from "../../services/RecipeService";


function AppointmentReviewBox() {

    const [appointment, setAppointment] = useState([])
    const [hasError, setError] = useState(false)
    const [open, setOpen] = React.useState(false);

    const conclusion = document.getElementById("conclusion")
    const recipes = document.getElementById("recipeList")

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
    },[])

async function fetchAppointment() {
        try {
            const response = await AppointmentService.get(localStorage.getItem("APPOINTMENT_ID"));
            setAppointment(response.data);
        } catch (error) {
            console.error(`Error loading appointment !: ${error}`);
        }
    }

    async function postAppointment() {
        console.log("entered")
            try {
                appointment.conclusion = conclusion.value
                appointment.status = "PASSED"
                console.log(appointment)
                await AppointmentService.finish(appointment)
            } catch (error) {
                console.error(`Error ocurred while updating the appointment: ${error}`);
            }
    }

    async function addRecipe(recipe) {
        try {
            await RecipeService.create(recipe)
        } catch (error) {
            console.error(`Error adding a new recipe!: ${error}`);
        }
    }

    const handleFinish = () => {
        if (checkValid()) {
            postAppointment().catch(err => setError(err))
            const recipeList = recipes.value.split("\n")
            Object.keys(recipeList).forEach(function (key) {
                let recipe = {
                    "recipe_id": Math.floor(Math.random() * 100000),
                    "description": recipeList[key].trim(),
                    "issueDate": appointment.date,
                    "nurseId": appointment.nurse.id,
                    "validated": false,
                    "patientBookId": appointment.patient.patient_book_id
                };

                if (recipe.description !== "") {
                    console.log(recipe)
                    addRecipe(recipe).catch(err => setError(err))
                }
            })
            window.location.assign("/patients")
        } else {
            alert("Please fill out all missing fields")
        }
    }

    function checkValid() {
        if (conclusion.value === "" || recipes.value === "")
            return false
        return true;

    }

    console.log(appointment)

    return (
    <>
        <div className="hospitalProfile-container">

            <h1>Appointment review</h1>

            <ul>
                <li className="info-row">
                    <TextField fullWidth className="input-margin" label="Conclusion" id="conclusion" type="text" variant="outlined" />
                </li>

                <li className="info-row">
                    <Button variant="outlined" color="primary" onClick={handleClickOpen}>
                        Add recipes
                    </Button>
                    <Dialog maxWidth open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
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

                <Button fullWidth size="large" color="success" onClick={() => handleFinish()}>
                    Submit
                </Button>
            </ul>

        </div>
    </>
    );
}

export default AppointmentReviewBox