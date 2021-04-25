import React from 'react';
import {Navbar, Nav} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import hospitallogo from '../hospital-logo.png';

const navbar = () => {
    return (
    <>
        <Navbar bg="dark" variant="dark">

            <Navbar.Brand href="#home">

                <img
                    src={hospitallogo}
                    width="30"
                    height="30"
                    className="d-inline-block align-top"
                    alt="hospital logo"
                />
            </Navbar.Brand>

            <Navbar.Brand href="#home">Our clinic</Navbar.Brand>
            <Nav.Link href="register">Register</Nav.Link>
            <Nav.Link href="login">Login</Nav.Link>
            <Nav.Link href="nursePage">Nurse page</Nav.Link>
            <Nav.Link href="recipes">Recipe approval</Nav.Link>
            <Nav.Link href="profile">Profile</Nav.Link>
            <Nav.Link href="registerRequests">Register requests (admin)</Nav.Link>
            <Nav.Link href="addAppointment">New appointment</Nav.Link>
            <Nav.Link href="appointments">All appointments (admin)</Nav.Link>
            <Nav.Link href="clinicProfile">Clinic profile (admin)</Nav.Link>
        </Navbar>
    </>
    );
};

export default navbar;





