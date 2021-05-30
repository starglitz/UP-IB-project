import {useHistory, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {UserService} from "../services/UserService";
import {Button} from "@material-ui/core";

const UpdateStaff = () => {

    const { id } = useParams();

    const history = useHistory();

    const [user, setUser] = useState({
        id: '',
        name:'',
        lastName:'',
        email:'',
        address:'',
        city:'',
        country:'',
        phoneNumber:'',
        enabled: true,
        roles: []
    })

    useEffect(() => {
        fetchUser(id);
    }, [id]);


    async function fetchUser(id) {
        try {
            const response = await UserService.get(id);
            setUser(response.data);
        } catch (error) {
            console.error(`Error while loading user with id ${id}: ${error}`);
        }
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setUser({ ...user, [name]: val });
    };

    async function editUser() {
        console.log("entered")
        console.log(user)
        if(validate()) {
            try {
                await UserService.edit(user.id, user);
                history.push("/blockUsers")
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


    return (
        <>
            <div className="registerInfoCard">
                <div style={{margin: '0 auto', display: 'flex',
                    justifyContent: 'center'}}>

                    <div className="register-form">



                        <label htmlFor="name" className="label-register">Name:</label>
                        <input defaultValue={user.name} onChange={handleFormInputChange("name")}
                               id="name" type="text" placeholder="enter your name here" className="input-register"/>

                        <label htmlFor="lastName" className="label-register">Last name:</label>
                        <input defaultValue={user.lastName} onChange={handleFormInputChange("lastName")}
                               id="lastName" type="text" placeholder="enter your last name here" className="input-register"/>

                        <label htmlFor="address" className="label-register">Address:</label>
                        <input defaultValue={user.address} onChange={handleFormInputChange("address")}
                               id="address" type="text" placeholder="enter your address here" className="input-register"/>

                        <label htmlFor="city" className="label-register">City:</label>
                        <input defaultValue={user.city} onChange={handleFormInputChange("city")}
                               id="city" type="text" placeholder="enter your city here" className="input-register"/>


                        <label htmlFor="country" className="label-register">Country:</label>
                        <input defaultValue={user.country} onChange={handleFormInputChange("country")}
                               id="country" type="text" placeholder="enter your country here" className="input-register"/>


                        <label htmlFor="email" className="label-register">Email:</label>
                        <input defaultValue={user.email} onChange={handleFormInputChange("email")}
                               id="email" type="text" placeholder="enter your email here" className="input-register"/>

                        <label htmlFor="phoneNumber" className="label-register">Phone number:</label>
                        <input defaultValue={user.phoneNumber} onChange={handleFormInputChange("phoneNumber")}
                               id="phoneNumber" type="text" placeholder="enter your phone number here" className="input-register"/>
                        <br/> <br/>
                        <Button variant="contained" color="secondary" onClick={editUser}>Edit</Button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default UpdateStaff;