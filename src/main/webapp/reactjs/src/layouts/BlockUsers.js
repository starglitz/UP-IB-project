import React, {useEffect, useState} from "react";
import {UserService} from "../services/UserService";
import {Button} from "@material-ui/core";
import {useHistory} from "react-router-dom";

const BlockUsers = () => {

    const [users, setUsers] = useState([]);
    const [refresh, setRefresh] = useState(true);

    const history = useHistory();

    useEffect(() => {
        fetchData()

    },[refresh])


    async function fetchData() {
        try {
            const response = await UserService.getAll();
            const filtered = response.data.filter(u => u.enabled === true &&
                !(u.roles.includes("CLINIC_ADMIN", "CLINIC_CENTRE_ADMIN")));
            //const filtered = response.data.filter(u => u.enabled === true)
             setUsers(filtered)
            console.log(filtered)
        } catch (error) {
            console.error(`Error loading users !: ${error}`);
        }
    }

    async function update(id) {

        const resp = await UserService.get(id);
        const user = resp.data
        console.log(user)
        const updated = {...user, enabled:false};
        console.log(updated)
        try {
            await UserService.edit(id, updated);
            //await ArticlesService.editArticle(id, article);
            setRefresh(!refresh)
        } catch (error) {
            console.error(`Error ocurred while blocking the user: ${error}`);
        }
    }

    const editStaff = (id) => {
        history.push("/updateStaff/" + id);
    };

    return (
        <> <div className="flex-container">

            <div className="flex-child" style={{justifyContent:'center', alignContent:'center', margin:'0 auto', textAlign:'center'}}>
            <br/> <h1> Manage users</h1>
                <br/>
            <table className="styled-table-2">
                <thead>
                <tr style={{backgroundColor:'#353A40', color:'wheat'}}>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role/Roles</th>
                    <th>Block user</th>
                    <th>Modify user info</th>
                </tr>
                </thead>
                <tbody>
                {users.map((u) =>
                    <tr>
                        <td>{u.id}</td>
                        <td>{u.name + " " +u.lastName}</td>
                        <td>{u.email}</td>
                        <td> {u.roles.map((r) => <p>{r}</p>)} </td>
                        <td> <Button variant="contained" color="secondary" onClick={() => update(u.id)}>Block this user</Button> </td>
                        <td>
                        {u.roles.includes("DOCTOR") || u.roles.includes("NURSE") ?
                            <Button variant="contained" color="primary" onClick={() => editStaff(u.id)}> Modify user profile </Button>
                            : <p> <b> Only clinic staff </b> info can be edited by admin</p>      }
                        </td>
                    </tr>

                )}
                </tbody>
            </table>
        </div>
        </div>
        </>
    )
}


export default BlockUsers;