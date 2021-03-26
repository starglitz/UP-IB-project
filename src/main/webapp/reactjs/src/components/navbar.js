import React from 'react';
import {Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';

const navbar = () => {
    return (
    <>
        <Navbar bg="dark" variant="dark">
            <Navbar.Brand href="#home">Our clinic</Navbar.Brand>

          {/*  <Navbar.Brand href="#home">*/}
          {/*ne znam zasto ne radi src do slike*/}
          {/*      <img*/}
          {/*          src="../../public/hospital-logo.png"*/}
          {/*          width="30"*/}
          {/*          height="30"*/}
          {/*          className="d-inline-block align-top"*/}
          {/*          alt="hospital logo"*/}
          {/*      />*/}
          {/*  </Navbar.Brand>*/}
        </Navbar>
    </>
    );
};

export default navbar;





