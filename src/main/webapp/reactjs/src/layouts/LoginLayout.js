import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import loginImg from '../login-img.jpg';

function LoginLayout(props) {

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


    return (
        <div className="login-container">
            <h2>User Login</h2>
            <img
                src={loginImg}
                alt="hospital logo"
            />

            <form className={classes.root}>

                <TextField id="outlined-basic" label="Email" variant="filled" />
                <TextField id="outlined-basic" label="Password" variant="filled" />


                <Button variant="contained" size="large" color="default">Login</Button>
            </form>
        </div>
    );
}

export default LoginLayout;