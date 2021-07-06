import React, {useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import loginImg from '../assets/login-img.jpg';
import {useHistory} from 'react-router-dom';
import { AuthenticationService } from '../services/AuthenticationService'
import {TokenService} from "../services/TokenService";
import {Checkbox, FormControlLabel} from "@material-ui/core";


const DEFAULT_LOGIN = {
    username: '',
    password: ''
};



const LoginLayout = () => {


    const [passwordLess, setPasswordLess] = useState(false)

    const useStyles = makeStyles((theme) => ({
        root: {
            display:'inline-block',
            width:'50%',
            margin:'20px',
            '& > *': {
                margin: '20px auto 20px auto',
                width: '25ch',
                display: 'block',

            },
        },
    }));
    const history = useHistory();
    const loggedUser = TokenService.getToken();
    if(loggedUser && loggedUser !== 'undefined'){
        history.push("/")
    }

    const classes = useStyles();

    const [credentials, setCredentials] = useState(DEFAULT_LOGIN);
    const [error, setError] = useState('');
    const [emailError, setEmailError] = useState('');

    const handleChange = (event, prop) => {
        setCredentials({
            ...credentials,
            [prop]: event.target.value
        });
    };
    const handlePasswordLess = (event, prop) => {
        setPasswordLess(event.target.checked)
        let password = document.getElementById('passwordTf')
        if (event.target.checked === true) {
            password.hidden = true
        } else {
            password.removeAttribute('hidden')
        }
    };

    const handleSubmit = (evt) => {
        evt.preventDefault();
        if (!passwordLess)
            login()
        else
            passwordLessLogin()
    }

    const login = async () => {
        const status = await AuthenticationService.login(credentials)
        console.log(status)
        if(status == '401') {
            setError("You are not allowed to use this application")
        }
        else if(status == '404') {
            setError("Wrong username or password")
        }
        else if(status == '200') {
            setError('ok!')
        }
    };

    const passwordLessLogin = async () => {
        const request = {
            'email':credentials.username
        }
        const status = await AuthenticationService.passwordLessRequest(request)
        if (status === '200') {
            alert('A redirection link has been sent to your email')
        } else if (status === '404')
            setEmailError('Email is not registered in our system')
    };


    return (

        <div className="login-container">
            <h2>User Login</h2>
            <img
                src={loginImg}
                alt="hospital logo"
            />

            <form className={classes.root} onSubmit={handleSubmit}>

                <TextField helperText={emailError} value={login.username} onChange={(event) =>
                    handleChange(event, 'username')}
                    id="outlined-basic username" label="Email" variant="filled" />
                <TextField helperText={error} value={login.password}
                    onChange={(event) =>
                    handleChange(event, 'password')}
                    id="passwordTf" label="Password" variant="filled" type='password' />

                <FormControlLabel
                    control={
                        <Checkbox
                            onChange={handlePasswordLess}
                            name="checkedB"
                        />
                    }
                    label="Password-less login"
                />

                <Button type='submit' variant="contained" size="large" color="default">Login</Button>
            </form>
        </div>
    );
}

export default LoginLayout;