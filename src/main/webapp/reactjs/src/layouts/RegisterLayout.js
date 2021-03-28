import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Button, Form} from "react-bootstrap";

const RegisterLayout = () => {
    return (
        <>
            <h1 style={{textAlign:'center', margin:'20px'}}>Register to our clinic</h1>

            <div style={{margin: '0 auto', display: 'flex',
                justifyContent: 'center'}}>

                <form action="#" method="post" className="register-form">

                    <label htmlFor="email" className="label-register">Email:</label>
                    <input id="email" type="text" placeholder="enter email here" className="input-register"/>

                    <label htmlFor="password" className="label-register">Password:</label>
                    <input id="password" type="password" placeholder="enter password here" className="input-register"/>

                    <label htmlFor="confirm" className="label-register">Confirm password:</label>
                    <input id="confirm" type="password" placeholder="confirm password" className="input-register"/>

                    <label htmlFor="name" className="label-register">Name:</label>
                    <input id="name" type="text" placeholder="enter your name here" className="input-register"/>

                    <label htmlFor="surname" className="label-register">Surame:</label>
                    <input id="surname" type="text" placeholder="enter your surname here" className="input-register"/>

                    <label htmlFor="address" className="label-register">Home address:</label>
                    <input id="address" type="text" placeholder="enter your address here" className="input-register"/>

                    <label htmlFor="city" className="label-register">City:</label>
                    <input id="city" type="text" placeholder="enter your city here" className="input-register"/>

                    <label htmlFor="state" className="label-register">State:</label>
                    <input id="state" type="text" placeholder="enter your state here" className="input-register"/>

                    <label htmlFor="name" className="label-register">Name:</label>
                    <input id="name" type="text" placeholder="enter your name here" className="input-register"/>

                    <label htmlFor="contact" className="label-register">Contact:</label>
                    <input id="contact" type="text" placeholder="enter your phone number here" className="input-register"/>

                    <label htmlFor="lbo" className="label-register">LBO:</label>
                    <input id="lbo" type="text" placeholder="enter your LBO here" className="input-register"/>

                    <input type="submit" value="submit" className="submit-register"/>
                </form>
            </div>
        </>
    );
};

export default RegisterLayout;