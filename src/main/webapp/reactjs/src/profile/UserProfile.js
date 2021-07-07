import profil_img from "../assets/profil-img.png";
import Button from "@material-ui/core/Button";
import SaveIcon from "@material-ui/icons/Save";
import React, {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import {AppointmentService} from "../services/AppointmentService";
import {PatientService} from "../services/PatientService";
import {UserService} from "../services/UserService";

const UserProfile = () => {


    const [user, setUser] = useState({
        id: '',
        address:'',
        city:'',
        country:'',
        email:'',
        lastName:'',
        name:'',
        phoneNumber:''
    });

    const [loading, setLoading] =  useState(true);
    const [disabled, setDisabled] =  useState(true);


    const handleEnableClik = () => {
        setDisabled(!disabled)
        let saveBtn = document.getElementById('save-btn');
        if (saveBtn.style.display === "initial") {
            saveBtn.style.display = "none";
        } else {
            saveBtn.style.display = "initial";
        }
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setUser({ ...user, [name]: val });
    };

    async function fetchData() {
        try {
            const response = await UserService.getMyInfo()
            setUser(response.data)
        } catch (error) {
            console.error(`Error loading user !: ${error}`);
        }
    }

    useEffect(() => {
        fetchData()
    }, []);




    async function editUser() {
        console.log("entered")
        console.log(user)
        if(validate()) {
            try {
                await UserService.editProfile(user.id, user);
                window.location.reload()
            } catch (error) {
                console.error(`Error ocurred while updating the user: ${error}`);
            }
        }
    }

    const validate = ()  => {
        let ok = true;

        if(user.name === "" || user.lastName === "" || user.address=== ""
            || user.city === "" || user.country === "" ) {
            ok = false;
            alert("Make sure to fill all fields!")
        }

        return ok;
    }


    const imgStyle = {
        display: 'block',
        margin: '20px auto 20px auto'
    };
    const btnStyle = {
        display: 'block',
        margin: '10px auto 10px auto',
        textAlign: 'center'
    };

    return (


        <>
            <h3 style={{textAlign:'center', margin:'20px', textTransform:'uppercase'}}>My profile</h3>
            <hr/>
            <img
                src={profil_img}
                width="200"
                height="200"
                style={imgStyle}
                alt="hospital logo"
            />

            <div>


                <label htmlFor="email" className="label-profilInfo">Email:</label>
                <input id="email" type="text" value={user.email}   className="input-profilInfo "
                       disabled = {(disabled)? "disabled" : ""}/>

                <label htmlFor="name" className="label-profilInfo">Name:</label>
                <input id="name" type="text" defaultValue={user.name} className="input-profilInfo "
                       disabled = {(disabled)? "disabled" : ""}
                       onChange={handleFormInputChange("name")}/>

                <label htmlFor="surname" className="label-profilInfo">Surname:</label>
                <input id="surname" type="text" defaultValue={user.lastName} className="input-profilInfo "
                       disabled = {(disabled)? "disabled" : ""}
                       onChange={handleFormInputChange("lastName")}/>

                <label htmlFor="address" className="label-profilInfo">Home address:</label>
                <input id="address" type="text" defaultValue={user.address} className="input-profilInfo "
                       disabled = {(disabled)? "disabled" : ""}
                       onChange={handleFormInputChange("address")}/>

                <label htmlFor="city" className="label-profilInfo">City:</label>
                <input id="city" type="text" defaultValue={user.city} className="input-profilInfo "
                       disabled = {(disabled)? "disabled" : ""}
                       onChange={handleFormInputChange("city")}/>

                <label htmlFor="state" className="label-profilInfo">State:</label>
                <input id="state" type="text" defaultValue={user.country} className="input-profilInfo "
                       disabled = {(disabled)? "disabled" : ""}
                       onChange={handleFormInputChange("country")}/>

                <label htmlFor="contact" className="label-profilInfo">Contact:</label>
                <input id="contact" type="text" defaultValue={user.phoneNumber} className="input-profilInfo "
                       disabled = {(disabled)? "disabled" : ""}
                       onChange={handleFormInputChange("name")}/>


                <div style={btnStyle}>
                    <Button  onClick = {handleEnableClik}
                             variant="contained" color="primary"  size="medium"> Edit </Button>

                    <Button id="save-btn" onClick={editUser}
                            variant="contained"
                            color="primary"
                            size="medium"
                            style={{margin: '15px', display: 'none', backgroundColor: '#d60808'}}
                            startIcon={<SaveIcon />}>Save</Button>
                </div>


            </div>
        </>
    );
}

export default UserProfile;