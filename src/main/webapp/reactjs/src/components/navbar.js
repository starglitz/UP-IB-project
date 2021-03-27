import React from 'react';
import {Navbar} from "react-bootstrap";
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
        </Navbar>
    </>
    );
};

export default navbar;





