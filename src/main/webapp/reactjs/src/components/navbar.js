import React from 'react';
import {Navbar, Nav} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import hospitallogo from '../assets/hospital-logo.png';
import {AuthenticationService} from "../services/AuthenticationService";
import {TokenService} from "../services/TokenService";

const navbar = () => {

    const logoutClick = () =>{
        AuthenticationService.logout()
    }

    let key = 1;
    const registerElement = <Nav.Link key={key++} href="register">Register</Nav.Link>
    const loginElement = <Nav.Link key={key++} href="login">Login</Nav.Link>
    const nursePageElement = <Nav.Link key={key++}  href="nursePage">Nurse page</Nav.Link>
    const recipeApprovalElement = <Nav.Link key={key++}  href="recipes">Recipe approval</Nav.Link>
    const profileElement = <Nav.Link key={key++}  href="profile">Profile</Nav.Link>
    const registerReqEleement = <Nav.Link key={key++}  href="registerRequests">Register requests (admin)</Nav.Link>
    const newAppointmentElement = <Nav.Link key={key++}  href="addAppointment">New appointment</Nav.Link>
    const allAppointmentsAdminElement = <Nav.Link key={key++} href="appointments">All appointments (admin)</Nav.Link>
    const clinicProfileAdminElement = <Nav.Link key={key++}  href="clinicProfile">Clinic profile (admin)</Nav.Link>
    const addClinic = <Nav.Link key={key++}  href="newClinic">Add clinic</Nav.Link>
    const addClinicAdmin = <Nav.Link key={key++}  href="newClinicAdmin">Add clinic admin</Nav.Link>
    const clinicsElement = <Nav.Link key={key++}  href="clinics">Clinics</Nav.Link>
    //const updateAppointmentElement = <Nav.Link key={key++}  href="updateAppointment">Update appointment</Nav.Link>
    const staffRegister = <Nav.Link key={key++}  href="staffRegister">Staff register</Nav.Link>
    const chandePassword = <Nav.Link key={key++}  href="changePassword">Change password</Nav.Link>
    const blockUsers = <Nav.Link key={key++}  href="blockUsers">Block users</Nav.Link>
    const doctorPatients = <Nav.Link key={key++}  href="patients">Patient</Nav.Link>
    const doctorAppointments = <Nav.Link key={key++}  href="calendar">Calendar</Nav.Link>
    const rate = <Nav.Link key={key++}  href="rate">Rate doctors & clinics</Nav.Link>
    const logoutLink = <Nav.Link key={key++} onClick={logoutClick}  href="">Logout</Nav.Link>
    const businessReport = <Nav.Link key={key++}  href="businessReports">Business Reports</Nav.Link>
    const appointmentHistory = <Nav.Link key={key++}  href="appointmentHistory">Appointment history</Nav.Link>
    const patientBook = <Nav.Link key={key++}  href="patientBook">Health card</Nav.Link>
    const myProfile = <Nav.Link key={key++}  href="userProfile">My profile</Nav.Link>


    let patient = [];
    patient.push(logoutLink)
    patient.push(profileElement)
    patient.push(clinicsElement)
    patient.push(chandePassword)
    patient.push(appointmentHistory)
    patient.push(patientBook)
    patient.push(rate)


    let nurse = [];
    nurse.push(logoutLink)
    nurse.push(nursePageElement)
    nurse.push(recipeApprovalElement)
    nurse.push(chandePassword)
    nurse.push(myProfile)

    let doctor = [];
    doctor.push(logoutLink)
    doctor.push(chandePassword)
    doctor.push(doctorPatients)
    doctor.push(doctorAppointments)
    doctor.push(myProfile)

    let clinicAdmin = [];
    clinicAdmin.push(logoutLink)
    clinicAdmin.push(newAppointmentElement)
    clinicAdmin.push(clinicProfileAdminElement)
  //  clinicAdmin.push(updateAppointmentElement)
    clinicAdmin.push(allAppointmentsAdminElement)
    clinicAdmin.push(staffRegister)
    clinicAdmin.push(chandePassword)
    clinicAdmin.push(blockUsers)
    clinicAdmin.push(businessReport)
    clinicAdmin.push(myProfile)

    let clinicCentreAdmin = [];
    clinicCentreAdmin.push(logoutLink)
    clinicCentreAdmin.push(registerReqEleement)
    clinicCentreAdmin.push(chandePassword)
    clinicCentreAdmin.push(addClinic)
    clinicCentreAdmin.push(addClinicAdmin)
    clinicCentreAdmin.push(myProfile)



    const elementRender = [];
    if(!TokenService.getToken()) {
        elementRender.push(registerElement)
        elementRender.push(loginElement)
    }
    if(TokenService.getToken()) {
        const role = AuthenticationService.getRole()


        role.forEach((rol) => {
            if (rol == "PATIENT") {
                elementRender.push(...patient)
            } else if (rol == "NURSE") {
                elementRender.push(...nurse)
            } else if (rol == "DOCTOR") {
                elementRender.push(...doctor)
            } else if (rol == "CLINIC_ADMIN") {
                elementRender.push(...clinicAdmin)
            } else if (rol == "CLINIC_CENTRE_ADMIN") {
                elementRender.push(...clinicCentreAdmin)
            }

        })
    }

    function getUniqueListBy(arr, key) {
        return [...new Map(arr.map(item => [item[key], item])).values()]
    }

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
                {getUniqueListBy(elementRender, "key")}
            </Navbar>
        </>
    );
};

export default navbar;