import React, {useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import loginImg from '../assets/login-img.jpg';
import {useHistory} from 'react-router-dom';
import { AuthenticationService } from '../services/AuthenticationService'


const DEFAULT_LOGIN = {
    username: '',
    password: ''
};

const LoginLayout = () => {

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

    const classes = useStyles();

    const [credentials, setCredentials] = useState(DEFAULT_LOGIN);
    const [error, setError] = useState('');
    const history = useHistory();
    const handleChange = (event, prop) => {
        setCredentials({
            ...credentials,
            [prop]: event.target.value
        });
    };

    const handleSubmit = (evt) => {
        evt.preventDefault();

       login().catch(err => { setError(err) });
    }

    const login = async () => {
        await AuthenticationService.login(credentials);
    };


    return (

        <div className="login-container">
            <h2>User Login</h2>
            <img
                src={loginImg}
                alt="hospital logo"
            />

            <form className={classes.root} onSubmit={handleSubmit}>

                <TextField value={login.username} onChange={(event) =>
                    handleChange(event, 'username')}
                    id="outlined-basic" label="Email" variant="filled" />
                <TextField helperText={error} value={login.password}
                    onChange={(event) =>
                    handleChange(event, 'password')}
                    id="outlined-basic" label="Password" variant="filled" type='password' />


                <Button type='submit' variant="contained" size="large" color="default">Login</Button>
            </form>
        </div>
    );
}

export default LoginLayout;