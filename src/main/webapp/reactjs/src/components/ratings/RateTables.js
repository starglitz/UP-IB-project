import React, {useEffect, useState} from "react";
import {DoctorService} from "../../services/DoctorService";
import {ClinicService} from "../../services/ClinicService";
import {Button} from "react-bootstrap";
import {Modal} from "react-bootstrap";

const RateTables = () => {

    const [doctors, setDoctors] = useState([]);
    const [clinics, setClinics] = useState([]);

    const [show1, setShow1] = useState(false);
    const handleClose1 = () => setShow1(false);
    const handleShow1 = () => setShow1(true);

    const [show2, setShow2] = useState(false);
    const handleClose2 = () => setShow2(false);
    const handleShow2 = () => setShow2(true);

    const [doctorRating, setDoctorRating] = useState(0);
    const [clinicRating, setClinicRating] = useState(0);

    const [error1, setError1] = useState("");
    const [error2, setError2] = useState("");

    const [random, setRandom] = useState(true);
    useEffect(() => {
        fetchDoctors()
        fetchClinics()
    }, [random]);

    async function fetchDoctors() {
        try {
            const response = await DoctorService.getNotRatedByPatient()
            setDoctors(response.data)
        } catch (error) {
            console.error(`Error loading doctors !: ${error}`);
        }
    }

    async function fetchClinics() {
        try {
            const response = await ClinicService.getNotRatedByPatient()
            setClinics(response.data)
        } catch (error) {
            console.error(`Error loading clinics !: ${error}`);
        }
    }

    async function rateClinic(clinic_id) {
        try {
            let rating = document.getElementById("clinic_rating").value;
            if(clinicRating < 1 || clinicRating > 5) {
                setError2("Rating must be between 1 and 5!")
            }
            else {
                let obj = {'rating': rating}
                const response = await ClinicService.rate(clinic_id, obj)
                alert("Successfully rated a clinic!")
                setRandom(!random)
                setError2("")
                handleClose2()
            }
        } catch (error) {
            console.error(`Error loading clinics !: ${error}`);
        }
    }

    async function rateDoctor(id) {
        try {
            let rating = document.getElementById("doctor_rating").value;
          //  console.log(rating)
            if(doctorRating < 1 || doctorRating > 5) {
                setError1("Rating must be between 1 and 5!")
              //  alert("Rating must be between 1 and 5")
            }
            else {
                let obj = {'rating': rating}
                const response = await DoctorService.rate(id, obj)
                alert("Successfully rated doctor!")
                setRandom(!random)
                setError1("")
                handleClose1()
            }
        } catch (error) {
            console.error(`Error loading doctors !: ${error}`);
        }
    }

    const handleChange1 = (event) => {console.log(event.target.value)
        setDoctorRating(event.target.value)
    };

    const handleChange2 = (event) => {console.log(event.target.value)
        setClinicRating(event.target.value)
    };

return(
        <>
            <div className='rating-div'>

            <h4 className='text-align'>As a patient, you can leave a rating for the doctors you've had an appointment with, and hospitals you've been to.</h4>

            <div className='flex-container'>

                <div className='flex-child'>
                <h3>Doctors</h3>
            <table className="styled-table">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th colSpan="2">Rate</th>
                </tr>
                {doctors.map((doctor) =>

                    <>
                    <tr>
                        <td>{doctor.id}</td>
                        <td>{doctor.user.name} {doctor.user.lastName}</td>
                        <td>{doctor.user.email}</td>
                        <td><Button onClick={handleShow1}>Rate this doctor</Button></td>
                    </tr>

                    <Modal show={show1} onHide={handleClose1}>
                    <Modal.Header closeButton>
                    <Modal.Title>Rate dr. {doctor.user.name} {doctor.user.lastName}</Modal.Title>
                    </Modal.Header>
                        <Modal.Body>Enter the rating in span of 1 to 5:    <input type="number" id="doctor_rating" onChange={(event) =>
                            handleChange1(event)} min='1' max='5'/>
                        <br/>
                           <p style={{color:'red'}}>  {error1} </p>
                        </Modal.Body>
                    <Modal.Footer>
                    <Button variant="primary" onClick={() => rateDoctor(doctor.id)}>
                    Rate
                    </Button>
                    </Modal.Footer>
                    </Modal>
                    </>
                )}
                </tbody>
            </table>

                </div>


                <div className='flex-child'>
                <h3>Clinics</h3>
            <table className="styled-table">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th colSpan="2">Rate</th>
                </tr>
                {clinics.map((clinic) =>
                    <>
                    <tr>
                        <td>{clinic.clinic_id}</td>
                        <td>{clinic.name}</td>
                        <td>{clinic.addressName}</td>
                        <td><Button onClick={handleShow2}>Rate this clinic</Button></td>

                    </tr>


                    <Modal show={show2} onHide={handleClose2}>
                    <Modal.Header closeButton>
                    <Modal.Title>Rate "{clinic.name}" clinic</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Enter the rating in span of 1 to 5:    <input type="number" id="clinic_rating" onChange={(event) =>
                        handleChange2(event)} min='1' max='5'/>
                        <br/>
                        <p style={{color:'red'}}>  {error2} </p>
                    </Modal.Body>
                    <Modal.Footer>

                    <Button variant="primary" onClick={() => rateClinic(clinic.clinic_id)}>
                    Rate
                    </Button>
                    </Modal.Footer>
                    </Modal>
                    </>
                )}
                </tbody>
            </table>
                </div>
            </div>
            </div>


        </>
    )

}

export default RateTables;