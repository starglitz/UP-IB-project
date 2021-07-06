import React, {useEffect, useState} from "react";
import {Button, makeStyles, MenuItem, Select} from "@material-ui/core";
import {NurseService} from "../services/NurseService";
import {DoctorService} from "../services/DoctorService";
import {ClinicService} from "../services/ClinicService";
import {useHistory} from "react-router-dom";

const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },
    select: {
    }
}));

const RegisterStaff = () => {

    const classes = useStyles();

    const history = useHistory();

    const [role, setRole] = useState('NURSE');
    const [showAlert, setShowAlert] = useState({ success: null, message: "" });
    const [user, setUser] = useState({
        address: '',
        city: '',
        country: '',
        email: '',
        lastName: '',
        name:'',
        password:'',
        enabled: true
    });

    const [clinics, setClinics] = useState([])
    const [error, setError] = useState([])
    //const [clinic, setClinic] = useState({})

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setUser({ ...user, [name]: val });
    };

    useEffect(() => {
        //fetchClinics().catch(err => setError(err));
    }, []);

    // async function fetchClinics() {
    //     try {
    //         const response = await ClinicService.getAll()
    //         setClinics(response.data)
    //     } catch (error) {
    //         console.error(`Error loading doctors !: ${error}`);
    //     }
    // }

    function handleChange(event) {
        console.log(event.target.value)
        //setClinic(event.target.value)
    }

    let sameData = <>
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

                <label htmlFor="password" className="label-register">Password:</label>
                <input defaultValue={user.password} onChange={handleFormInputChange("password")}
                       name="password" id="password" type="password" placeholder=""
                       maxLength="100" onKeyUp={passwordChanged} className="input-register"/>
                <span id="strength2">Type Password</span>

                <label htmlFor="confirm" className="label-register">Confirm password:</label>
                <input  id="confirm" type="password" placeholder="confirm password" className="input-register"/>



            </div>
        </div>
    </>

    function passwordChanged() {
        var strength = document.getElementById('strength2');
        var strongRegex = new RegExp("^(?=.{14,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
        var mediumRegex = new RegExp("^(?=.{10,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
        var enoughRegex = new RegExp("(?=.{8,}).*", "g");
        var pwd = document.getElementById("password");
        if (pwd.value.length === 0) {
            strength.innerHTML = 'Type Password';
        } else if (false === enoughRegex.test(pwd.value)) {
            strength.innerHTML = 'More Characters';
        } else if (strongRegex.test(pwd.value)) {
            strength.innerHTML = '<span style="color:green">Strong! 👌</span>';
        } else if (mediumRegex.test(pwd.value)) {
            strength.innerHTML = '<span style="color:orange">Medium! 😐</span>';
        } else {
            strength.innerHTML = '<span style="color:red">Weak! 😡</span>';
        }
    }


    const renderAuthButton = () => {

        if(role === 'NURSE') {
            return <>
                <Button onClick={registerNurse} className='register-btn' variant="contained" color="secondary">
                Register as nurse
            </Button> </>;
        }
        else if(role === 'DOCTOR') {
            return (
                <>
                    <Button onClick={registerDoctor} className='register-btn' variant="contained" color="secondary">
                        Register as doctor
                    </Button>
                </>);
        }
    }

    const setRoleDoctor = () => {
        setRole('DOCTOR');
    }

    const setRoleNurse = () => {
        setRole('NURSE');
    }

    const validate = ()  => {
        let ok = true;
        let confirm = document.getElementById('confirm').value;
        if(user.password === ""  || confirm === "" || user.name === "" ||
            user.lastName === "" || user.address=== "" || user.city === "" ||
        user.country === "" || user.email === "") {
            ok = false;
            alert("Make sure to fill all fields!")
        }

        else if(user.password.length < 8) {
            ok = false;
            alert("Password should be at least 8 characters long! 😡")
        }
        else if(user.password !== confirm) {
            ok = false;
            alert("Passwords don't match!")
        }

        else if(!validateEmail(user.email)) {
            ok = false;
            alert("Email is in invalid format!")
        }

        return ok;
    }

    const validateEmail = (email)  => {
        let mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if(email.match(mailformat)) {
            return true;
        }
        return false;
    }


    async function registerNurse () {

        if (validate) {

            let nurse = {"user": user}
            console.log(nurse)
            console.log("NURSE REGISTER")
            try {
                await NurseService.add(nurse)
                setShowAlert({ success: true, message: "Successfully registered" });
                alert("successfully registered!")
                history.push("/")

            } catch (error) {
                console.error(`Error occurred while registering: ${error}`);
                alert("Bad request! Make sure your email is unique")

                setShowAlert({
                    success: false,
                    message: "Error ocurred while registering",
                });
            }
         //   alert("successfully registered!")
          //  window.location.assign("/home");
        }
    }


    async function registerDoctor () {
        console.log("im here")


        if (validate) {
            let doctor = {"user": user};
            console.log(doctor)
            console.log("DOCTOR REGISTER")
            try {
                await DoctorService.add(doctor);
                setShowAlert({ success: true, message: "Successfully registered" });
                alert("successfully registered!")
                history.push("/")
            } catch (error) {
                console.error(`Error occurred while registering: ${error}`);
                alert("Bad request! Make sure your email is unique")
                setShowAlert({
                    success: false,
                    message: "Error ocurred while registering",
                });
            }
            alert("successfully registered!")
           // window.location.assign("/home");
        }
    }

    return (
        <>

            <div className="registerInfoCard">

                <h3>Register</h3>

                <div className="radios">
                    <input type="radio" id="doctor" name="usertype" value="doctor" defaultChecked onClick={setRoleNurse}/>
                    <label className="labelRadio left" htmlFor="male">Register as nurse</label>

                    <input type="radio" id="nurse" name="usertype" className="right" value="nurse" onClick={setRoleDoctor}/>
                    <label className="labelRadio" htmlFor="female">Register as doctor</label>

                </div>
                {sameData}
                <br/>

                {renderAuthButton()}
            </div>
        </>
    );
}


export default RegisterStaff;