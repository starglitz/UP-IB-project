import React, {useEffect, useState} from 'react';
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {classes} from "istanbul-lib-coverage";
import {AppointmentService} from "../../services/AppointmentService";
import {RecipeService} from "../../services/RecipeService";

export const RecipeTable = () => {

    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            // .then(res => setRequests(res))
            // .catch(err => setError(err));
    },[])

    // async function fetchData() {
    //     const res = await fetch('http://localhost:8080/notApprovedRecipes');
    //     return res.json()
    // }

    async function fetchData() {
        try {
            const response = await RecipeService.getNotApproved()
            setRequests(response.data)
        } catch (error) {
            console.error(`Error loading recipes !: ${error}`);
        }
    }

    console.log(requests)


    async function approveRecipe(recipe) {
        recipe.validated = true
        try {
            await RecipeService.approve(recipe.recipe_id.toString(), recipe)
            //history.push("/home")
        } catch (error) {
            console.error(`Error ocurred while updating the recipe: ${error}`);
        }
    }

    // const approveRecipe = (recipe) => {
    //     recipe.validated = true
    //     fetch('http://localhost:8080/updateRecipe/' + recipe.recipe_id.toString(), {
    //         method: 'PUT',
    //         headers: {
    //             'Content-Type': 'application/json',
    //         },
    //         body: JSON.stringify(recipe),
    //     })
    //         .then(response => response.json())
    //         .then(rcp => {
    //             requests.splice(rcp) // removes the clicked recipe from the list
    //             console.log('Success:', rcp);
    //             window.location.reload();
    //         })
    //         .catch((error) => {
    //             console.error('Error:', error);
    //         });
    // }


    return (
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"} >id</TableCell>
                        <TableCell align={"center"} >Description</TableCell>
                        <TableCell align={"center"} >Date</TableCell>
                        <TableCell align={"center"} >Actions</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {requests.map(row => (
                        <TableRow key={row.name}>
                            <TableCell align={"center"} >{row.recipe_id}</TableCell>
                            <TableCell align={"center"} >{row.description}</TableCell>
                            <TableCell align={"center"} >{row.issueDate}</TableCell>
                            <TableCell align={"center"} >
                                <Button variant="contained" color="primary" onClick={() => {approveRecipe(row)}}>
                                    Approve Recipe
                                </Button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}