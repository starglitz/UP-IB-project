import React, {useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import loginImg from '../assets/login-img.jpg';
import {useHistory} from 'react-router-dom';
const DEFAULT_LOGIN = {
    email: '',
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

    const [login, setLogin] = useState(DEFAULT_LOGIN);
    const [error, setError] = useState('');
    const history = useHistory();
    const handleChange = (event, prop) => {
        setLogin({
            ...login,
            [prop]: event.target.value
        });
    };

    const handleSubmit = (evt) => {
        evt.preventDefault();

        const data = {"email":login.email, "password":login.password};

        fetch('http://localhost:8080/loginData', {
            method: 'POST', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response =>{
                if(response.status!==200){
                    throw new Error(response.status)
                    }
                else {
                    history.push('/profile')
                }}  )
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                setError('Pogresni podaci')
                console.error('Error:', console.warn(error));
            });


    }


    return (

        <div className="login-container">
            <h2>User Login</h2>
            <img
                src={loginImg}
                alt="hospital logo"
            />

            <form className={classes.root} onSubmit={handleSubmit}>

                <TextField value={login.email} onChange={(event) =>
                    handleChange(event, 'email')}
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