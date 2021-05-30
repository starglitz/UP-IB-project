import React, {useEffect, useState} from "react";
import {Button} from "@material-ui/core";
import {AuthenticationService} from "../services/AuthenticationService";
import {useHistory, useLocation} from "react-router-dom";
import {UserService} from "../services/UserService";

const ChangePassword = () => {
    const location = useLocation();
    const history = useHistory();

    const [user, setUser] = useState({});


    useEffect(() => {
        fetchMe()
    }, []);

    async function fetchMe() {
        try {
            const response = await UserService.getMyInfo()
            // setUser(response.data);
            setUser(response.data)
            console.log(response.data);
        } catch (error) {
            console.error(`Error loading your profile !: ${error}`);
        }
    }


    async function change() {
        console.log("ehh")
        let old = document.getElementById("old").value;
        let newPass = document.getElementById("new").value;
        if(validate(old, newPass)) {

            let edited = {...user, "passwordValidate":old};
            edited.password = newPass;
            // edited.password = newPass;
            // edited.passwordValidate = old;
            //console.log(edited);
            try {
                    console.log(user)
                    const status = await UserService.edit(user.id, edited)
                    console.log("status here: " + status)
                    if(status == '200') {
                        history.push("/home")
                    }
                    else if(status == '400') {
                        alert("The old password is incorrect!")
                    }

            } catch (error) {
                console.error(`Error ocurred while updating your info: ${error}`);
            }
        }
    }

    const validate = (old, newPass) => {
        let ok = true
        if(old === "") {
            alert("You left old password field empty!")
            ok = false
        }
        else if(newPass === "") {
            alert("You left new password field empty!")
            ok = false
        }
        return ok
    }

    return(
        <>
            <div className="updateInfoCard">
                <div className="passwordChange">
                <table style={{margin: '0 auto', width:'50%',textAlign:'center', whiteSpace:'nowrap'}}>
                    <tr>
                            <td>
                                <label htmlFor="password" className="label-register"> Old password:</label>
                            </td>
                            <td >
                                <input name="password" id="old" type="password" placeholder="enter old password here"
                                       maxLength="100" className="input-register" />
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <label htmlFor="confirm" className="label-register">New password:</label>
                            </td>
                            <td >
                                <input  id="new" type="password" placeholder="enter new password" className="input-register"/>
                            </td>
                        </tr>
                    </table>
                    <br/>


                    <Button variant="contained" onClick={change} className="btnChange">Change</Button>
                </div>
            </div>
        </>
    )
}

export default ChangePassword;