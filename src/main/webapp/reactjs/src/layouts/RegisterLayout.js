import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { useHistory } from "react-router-dom";
import {PatientService} from "../services/PatientService";
import {TokenService} from "../services/TokenService";


const RegisterLayout = () => {

    const history = useHistory();
    const [user, setUser] = useState({
            address: '',
            city:'',
            country:'',
            email:'',
            lastName:'',
            name:'',
            password:'',
            phoneNumber:''
    })

    const [patient, setPatient] = useState( {
        lbo: '',
        enabled: true,
        approved: false
    })

    const loggedUser = TokenService.getToken();
    if(loggedUser && loggedUser !== 'undefined'){
        history.push("/")
    }


    const routeChange = () =>{
        let path = `/`;
        history.push(path);
    }

    async function register(user) {
        try {
            await PatientService.create(user);
        }
        catch (error) {
            console.error(`Error creating user! : ${error}`);
        }
    }

    const sendData = ()  => {

         let confirm = document.getElementById('confirm').value;

        console.log(user);
        console.log(patient)
         if(validateForm(confirm)) {
        handleClickOpen();

        console.log(user);
        console.log(patient)

        let copy = {...patient, "userDto": user}
        console.log(copy)

        register(copy)

         }
    }

        const validateForm = (confirm)  => {
            let ok = true;
            if(user.email === "" || user.password === ""  || confirm === "" ||
                user.name === "" || user.lastName === "" || user.address === "" ||
                user.city === "" || user.state === "" || user.phoneNumber === "" ||
                patient.lbo === "") {
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

            else if(validateEmail(user.email) === false) {
                ok = false;
                alert("You have entered an invalid email address!")
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



        function passwordChanged() {
        var strength = document.getElementById('strength');
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


    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setPatient({ ...patient, [name]: val });
    };

    const handleFormInputChangeUser = (name) => (event) => {
        const val = event.target.value;
        setUser({ ...user, [name]: val });
    };

    return (
        <>
            <h1 style={{textAlign:'center', margin:'20px'}}>Register to our clinic</h1>

            <div style={{margin: '0 auto', display: 'flex',
                justifyContent: 'center'}}>

                <div className="register-form">
                {/*<form method="post" className="register-form">*/}


                    <label htmlFor="email" className="label-register">Email:</label>
                    <input onChange={handleFormInputChangeUser("email")} id="email" type="text" placeholder="enter email here" className="input-register"/>


                    <label htmlFor="password" className="label-register">Password:</label>
                    <input name="password" id="password" type="password" placeholder="enter password here"
                           maxLength="100" onKeyUp={passwordChanged} onChange={handleFormInputChangeUser("password")} className="input-register"/>
                    <span id="strength">Type Password</span>


                    <label htmlFor="confirm" className="label-register">Confirm password:</label>
                    <input  id="confirm" type="password" placeholder="confirm password" className="input-register"/>

                    <label htmlFor="name" className="label-register">Name:</label>
                    <input onChange={handleFormInputChangeUser("name")} id="name" type="text" placeholder="enter your name here" className="input-register"/>

                    <label htmlFor="surname" className="label-register">Surname:</label>
                    <input onChange={handleFormInputChangeUser("lastName")} id="surname" type="text" placeholder="enter your surname here" className="input-register"/>

                    <label htmlFor="address" className="label-register">Home address:</label>
                    <input onChange={handleFormInputChangeUser("address")} id="address" type="text" placeholder="enter your address here" className="input-register"/>

                    <label htmlFor="city" className="label-register">City:</label>
                    <input onChange={handleFormInputChangeUser("city")} id="city" type="text" placeholder="enter your city here" className="input-register"/>

                    <label htmlFor="state" className="label-register">State:</label>
                    <input onChange={handleFormInputChangeUser("country")} id="state" type="text" placeholder="enter your state here" className="input-register"/>


                    <label htmlFor="contact" className="label-register">Contact:</label>
                    <input onChange={handleFormInputChangeUser("phoneNumber")} id="contact" type="text" placeholder="enter your phone number here" className="input-register"/>

                    <label htmlFor="lbo" className="label-register">LBO:</label>
                    <input onChange={handleFormInputChange("lbo")} id="lbo" type="text" placeholder="enter your LBO here" className="input-register"/>

                    <button onClick={sendData} className="submit-register">Submit</button>
                {/*</form>*/}
                </div>
            </div>

            <Dialog
                open={open}
                keepMounted
                onClose={handleClose}
                aria-labelledby="alert-dialog-slide-title"
                aria-describedby="alert-dialog-slide-description"
            >
                <DialogTitle id="alert-dialog-slide-title">{"Success"}</DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-slide-description">
                        You have successfully sent a registering request. Wait for our clinic administrator to approve it, before you can use this profile.
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    {/*onClick={handleClose}*/}

                    <Button  onClick={()=>{ handleClose(); routeChange() }} color="primary">
                        ok
                    </Button>
                </DialogActions>
            </Dialog>


        </>
    );
};

export default RegisterLayout;
