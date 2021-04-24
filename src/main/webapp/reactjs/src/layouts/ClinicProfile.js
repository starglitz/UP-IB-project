import React, {useEffect, useState} from "react";
import AppointmentRow from "../components/AppointmentRow";
import DoctorRow from "../components/doctorRow";

const ClinicProfile = () => {

    const [clinic, setClinic] = useState({

        clinic_id: '',
        name: '',
        description: '',
        rating: '',
        priceList: []
    });

    const [doctors, setDoctors] = useState([]);

    const [hasError, setErrors] =  useState(false);

    useEffect(() => {
        fetchData()
        fetchDoctors()
    }, []);

    async function fetchData() {
        const res = await fetch("http://localhost:8080/clinic/1");
        res
            .json()
            .then(res => setClinic(res))
            .catch(err => setErrors(err));
    }

    async function fetchDoctors() {
        const res = await fetch("http://localhost:8080/doctorByClinic/1");
        res
            .json()
            .then(res => setDoctors(res))
            .catch(err => setErrors(err));
    }

    return (
        <>


            <h1 style={{textAlign:'center', margin:'20px'}}>Hospital profile</h1>

            <div style={{margin: '0 auto', display: 'flex',
                justifyContent: 'center'}}>

                <div className="register-form">

                    <label htmlFor="name" className="label-register">Name:</label>
                    <input defaultValue={clinic.name} readOnly id="name" type="text"  className="input-register"/>

                    <label htmlFor="description" className="label-register">Time:</label>
                    <input defaultValue={clinic.description} readOnly id="description" type="text" className="input-register"/>

                    <label htmlFor="rating" className="label-register">Rating:</label>
                    <input defaultValue={clinic.rating} readOnly id="rating" type="text" className="input-register"/>


                    <table className="requestsTable appointmentsTable">
                        <tbody>
                        <tr>
                            <th>Doctor ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Contact</th>
                        </tr>

                        {doctors.map((doctor) =>
                            <DoctorRow key={doctor.id} id={doctor.id} name={doctor.name + " " + doctor.lastName}
                            email={doctor.email} phoneNumber={doctor.phoneNumber}/>
                        )}
                        </tbody>
                    </table>

                    {/*<label htmlFor="nurse" className="label-register">Nurse:</label>*/}
                    {/*<select name="nurse" id="nurse" className="input-register">*/}
                    {/*    {nurses.map((nurse) =>*/}
                    {/*        <option key={nurse.id} value={JSON.stringify(nurse)}>{nurse.name + " " + nurse.lastName}</option>*/}
                    {/*    )}*/}
                    {/*</select>*/}

                    {/*<label htmlFor="price" className="label-register">Price:</label>*/}
                    {/*<input defaultValue={appointment.price} id="price" type="text" placeholder="enter price" className="input-register"/>*/}

                    {/*<button className="submit-register" onClick={sendData}>Submit</button>*/}
                </div>
            </div>

        </>
    )

}


export default ClinicProfile;