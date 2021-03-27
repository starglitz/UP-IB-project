import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import {Button, Form} from "react-bootstrap";

const RegisterLayout = () => {
    return (
        <>
            <h1 style={{textAlign:'center', margin:'30px'}}>Register to our clinic</h1>
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center'
            }}>

            <Form>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" />

                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Form.Group controlId="confirmPassword">
                    <Form.Label>Confirm password</Form.Label>
                    <Form.Control type="password" placeholder="Confirm password" />
                </Form.Group>

                <Form.Group controlId="formName">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="name" placeholder="Enter name" />

                </Form.Group>

                <Form.Group controlId="formSurname">
                    <Form.Label>Surname</Form.Label>
                    <Form.Control type="surname" placeholder="Enter surname" />

                </Form.Group>

                <Form.Group controlId="formAddress">
                    <Form.Label>Home address</Form.Label>
                    <Form.Control type="address" placeholder="Enter address" />

                </Form.Group>

                <Form.Group controlId="formCity">
                    <Form.Label>City</Form.Label>
                    <Form.Control type="city" placeholder="Enter your city" />

                </Form.Group>

                <Form.Group controlId="formState">
                    <Form.Label>State</Form.Label>
                    <Form.Control type="state" placeholder="Enter state" />

                </Form.Group>

                <Form.Group controlId="formContact">
                    <Form.Label>Contact</Form.Label>
                    <Form.Control type="contact" placeholder="Enter your phone number" />

                </Form.Group>

                <Form.Group controlId="formLBO">
                    <Form.Label>LBO</Form.Label>
                    <Form.Control type="lbo" placeholder="Enter your LBO" />

                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
            </div>
        </>
    );
};

export default RegisterLayout;