import React, {useEffect, useState} from "react";
import AppointmentRow from "../components/AppointmentRow";
import DoctorRow from "../components/doctorRow";
import {useHistory} from "react-router-dom";
import ServicesTable from "../components/tables/ServicesTable";
import Button from "@material-ui/core/Button";
import SaveIcon from "@material-ui/icons/Save";
import PatientsTable from "../components/patients/PatientsTable";
import Location from "../components/Location";
import {AppointmentService} from "../services/AppointmentService";
import {ClinicService} from "../services/ClinicService";
import {DoctorService} from "../services/DoctorService";

const ClinicProfile = () => {

    const [clinic, setClinic] = useState({

        clinic_id: '',
        name: '',
        description: '',
        rating: '',
        priceList: [],
        addressName:'',
        lat:'',
        lng:''
    });

    // const [address, setAddress] = useState({
    // });

    const [doctors, setDoctors] = useState([]);

    const [appointments, setAppointments] = useState([]);

    const [hasError, setErrors] =  useState(false);

    const [random, setRandom] = useState(Math.random());

    const [disabled, setDisabled] = useState(true);

    let history = useHistory();

    useEffect(() => {
        fetchData()
        fetchDoctors()
        fetchAppointments()
        // fetchAddress()
        //console.log(address)
    }, []);

    // async function fetchData() {
    //     const res = await fetch("http://localhost:8080/clinic/1");
    //     res
    //         .json()
    //         .then(res => setClinic(res))
    //         .catch(err => setErrors(err));
    // }

    async function fetchData() {
        try {
            const response = await ClinicService.get(17);
            setClinic(response.data)
            console.log(response.data)
        } catch (error) {
            console.error(`Error loading clinic !: ${error}`);
        }
    }

    // async function fetchDoctors() {
    //     const res = await fetch("http://localhost:8080/doctorByClinic/1");
    //     res
    //         .json()
    //         .then(res => setDoctors(res))
    //         .catch(err => setErrors(err));
    // }

    async function fetchDoctors() {
        try {
            const response = await DoctorService.getByClinicId(17)
            setDoctors(response.data)
            console.log(response.data);
        } catch (error) {
            console.error(`Error loading doctors !: ${error}`);
        }
    }


    // async function fetchAppointments() {
    //     const res = await fetch("http://localhost:8080/appointments/free/clinic/1");
    //     res
    //         .json()
    //         .then(res => res.filter(app => app.deleted === false))
    //         .then(res => setAppointments(res))
    //         .catch(err => setErrors(err));
    // }

    async function fetchAppointments() {
        try {
            const response = await AppointmentService.getByClinicId(17);
            setAppointments(response.data.filter((app) => app.deleted == false && app.status == 'FREE'))
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }

    // async function fetchAddress() {
    //     const res = await fetch("http://localhost:8080/address/clinic/1");
    //     res
    //         .json()
    //         .then(res => setAddress(res))
    //         .then(res => console.log(res))
    //         .catch(err => setErrors(err));
    // }
    //
    // async function fetchAddress() {
    //     try {
    //         const response = await
    //         const response = await AppointmentService.getByClinicId(id);
    //         setRequests(response.data)
    //     } catch (error) {
    //         console.error(`Error loading appointments !: ${error}`);
    //     }
    // }




    let delete_appointment = (appointment_id) => {
        console.log("delet appointment clicked")
        let appointment = {deleted:true, appointment_id:appointment_id};
        setAppointments(appointments.filter(app => app.deleted === false))
        setRandom(Math.random())
        sendDataDelete(appointment_id);
    }



    const update_appointment = (appointment_id) => {
        history.push({
            pathname: '/updateAppointment',
            search: '?id=' + appointment_id,
            state: { detail: appointment_id}
        });
    };

    async function sendDataDelete(appointment_id) {
            try {
                await AppointmentService.deleteAppointment(appointment_id)
                setAppointments((appointments) => appointments.filter((appointment) => appointment.appointment_id !== appointment_id));
            } catch (error) {
                console.error(`Error ocurred while deleting appointment : ${error}`);
            }
    }


     const handleEnableClick = () => {
        setDisabled(!disabled);
       // this.setState( {disabled: !this.state.disabled} )
        let saveBtn = document.getElementById('save-btn');
        if (saveBtn.style.display === "initial") {
            saveBtn.style.display = "none";
        } else {
            saveBtn.style.display = "initial";
        }
    }

    async function handleSaveData(){
        let name = document.getElementById('name').value;
        let description = document.getElementById('description').value;
        let rating = document.getElementById('rating').value;

        let hospital = {clinic_id:'1', name:name, description: description, rating: rating}

        // fetch('http://localhost:8080/clinic', {
        //     method: 'PUT',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify(hospital),
        // })
        //     .then(response => response.json())
        //     .then(hospital => {
        //         console.log('Success:', hospital);
        //         window.location.reload();
        //     })
        //     .catch((error) => {
        //         console.error('Error:', error);
        //     });

        await ClinicService.update(hospital.clinic_id, hospital);
    }


    return (
        <>

            <div className="flex-container">

                <div className="flex-child">



            <div style={{display: 'flex', justifyContent:'center'}}>



                <div className="hospital-info">

                    <h1 style={{textAlign:'center',color: 'wheat'}}>Clinic info</h1>
                    <table style={{marginTop:'30px'}}>
                        <tbody>
                    <tr>
                        <td>
                    <label htmlFor="name" className="label-hospital">Name:</label></td>
                        <td>  <input defaultValue={clinic.name} id="name" type="text"  className="input-hospital"
                                     disabled = {(disabled)? "disabled" : ""}/>
                        </td>
                    </tr>
                        <tr>
                            <td>
                    <label htmlFor="description" className="label-hospital">Description:</label>
                            </td>
                            <td>  <input defaultValue={clinic.description} id="description" type="text" className="input-hospital"
                                         disabled = {(disabled)? "disabled" : ""}/>
                            </td>
                        </tr>
                        <tr>
                            <td>  <label htmlFor="rating" className="label-hospital">Rating:</label>
                            </td>
                            <td>   <input defaultValue={clinic.rating}  id="rating" type="text" className="input-hospital"
                                          disabled/>
                            </td>
                            </tr>


                    <tr>
                        <td>  <label htmlFor="addr" className="label-hospital">Address:</label>
                        </td>
                        <td>   <input defaultValue={clinic.addressName}  id="rating" type="text" className="input-hospital"
                                      disabled = {(disabled)? "disabled" : ""}/>
                        </td>
                    </tr>

                        <tr><td><Button  onClick = {handleEnableClick}
                                         variant="contained" color="primary"  size="medium"> Edit </Button></td>

                        <td><Button id="save-btn" onClick={handleSaveData}
                                    variant="contained"
                                    color="primary"
                                    size="medium"
                                    style={{margin: '15px', display: 'none', backgroundColor: '#d60808'}}
                                    startIcon={<SaveIcon />}>Save</Button></td>
                        </tr>
                        </tbody>




                    </table>
                    <br/>
                    <Location name={clinic.addressName} lat={clinic.lat} lng={clinic.lng}></Location>
              <br/>

                </div>



                <div className="flex-child right-part" style={{marginTop:'40px'}}>
                    <ServicesTable hospital_id="1"/>


                    <h1>Doctors of this clinic</h1>

                    <table className="styled-table">
                        <thead>
                        <tr>
                            <th>Doctor ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Contact</th>
                        </tr>
                        </thead>
                        <tbody>
                        {doctors.map((doctor) =>
                            <DoctorRow key={doctor.user.id} id={doctor.user.id} name={doctor.user.name + " " + doctor.user.lastName}
                            email={doctor.user.email} phoneNumber={doctor.user.phoneNumber}/>
                        )}
                        </tbody>
                    </table>
                </div>
            </div>

                    <div>
                    <h1 style={{textAlign:'center', marginTop:'40px', marginBottom:'20px'}}>Free appointments at this clinic</h1>

                    <table className="styled-table centered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Status</th>
                            <th>Date</th>
                            <th>Start time</th>
                            <th>End time</th>
                            <th>Doctor</th>
                            <th>Nurse</th>
                            <th>Price</th>
                            <th colSpan="2">Delete/Modify an appointment</th>
                        </tr>
                        </thead>
                        <tbody>

                        {appointments.filter(app => app.deleted === false && app.status == 'FREE').map((app) =>
                            <AppointmentRow deleteAppointment={delete_appointment} updateAppointment={update_appointment}
                                            status={app.status} key={app.appointment_id} date={app.date} start={app.start}
                                            end={app.end} id={app.appointment_id} doctor={app.doctor}
                                            nurse={app.nurse} price={app.price} deleted={app.deleted}/>

                        )}
                        </tbody>
                    </table>

                    </div>
                </div>
            </div>

        </>
    )

}


export default ClinicProfile;