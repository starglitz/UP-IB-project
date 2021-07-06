import React, {useEffect, useState} from 'react';
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {classes} from "istanbul-lib-coverage";
import {RecipeService} from "../../services/RecipeService";
import {TokenService} from "../../services/TokenService";
import {UserService} from "../../services/UserService";
import {NurseService} from "../../services/NurseService";
import {useLocation} from "react-router-dom";

export const RecipeTable = () => {

    const location = useLocation();
    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData().catch(err => setError(err));
    },[])

    async function fetchData() {
        try {
            const decoded_token = TokenService.decodeToken(TokenService.getToken().sub());
            const user = await UserService.getByEmail(decoded_token.sub)

            console.log(user.data)

            const response = await RecipeService.getNotApproved(user.data.id)
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
            window.location.reload();
        } catch (error) {
            console.error(`Error ocurred while updating the recipe: ${error}`);
        }
    }


    return (
        <div  className="form-size" >
            <TableContainer component={Paper} >
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
                                    <Button variant="contained" color="primary" onClick={() => {approveRecipe(row).catch(err => setError(err))}}>
                                        Approve Recipe
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}