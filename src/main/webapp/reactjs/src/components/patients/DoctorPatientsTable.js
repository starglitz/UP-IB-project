import React, {useEffect, useState} from 'react';
import MUIDataTable, { TableFilterList }  from "mui-datatables";
import {useHistory} from "react-router-dom";
import {Dialog, DialogActions, DialogContent, DialogTitle, Tooltip} from "@material-ui/core";
import TextField from '@material-ui/core/TextField';
import {AppointmentService} from "../../services/AppointmentService";
import {PatientService} from "../../services/PatientService";
import Button from "@material-ui/core/Button";

const DoctorPatientsTable = () => {

    const [open, setOpen] = React.useState(false);
    const [patients, setPatients] = useState([])
    const [hasError, setError] = useState(false)
    const [filter, setFilter] = useState(false);
    const history = useHistory();


    useEffect(() => {
        fetchData().catch(err => setError(err))
    },[])


    async function fetchData() {
        try {
            const response = await PatientService.getByCurrentDoctor()
            setPatients(response.data)
            let pat = response.data

            let list = []
            pat.forEach(patient => {
                list.push({approved: patient.approved,
                    enabled: patient.enabled,
                    lbo: patient.lbo,
                    patientBookId: patient.patientBookId,
                    address:patient.userDto.address,
                    city: patient.userDto.city,
                    country: patient.userDto.country,
                    email: patient.userDto.email,
                    id: patient.userDto.id,
                    lastName: patient.userDto.lastName,
                    lastPasswordResetDate: patient.userDto.lastPasswordResetDate,
                    name: patient.userDto.name,
                    phoneNumber: patient.userDto.phoneNumber,
                    });

            } )
            setPatients(list)
            console.log(list)
        } catch (error) {
            console.error(`Error loading patients !: ${error}`);
        }
    }

    const listenerHandler = (e) => {
        setOpen(true);
        console.log(e)
        localStorage.setItem("PATIENT_ID", e[0])
        localStorage.setItem("PATIENT_NAME", e[1] + " " + e[2])
    }

    const columns = [
        {
            label: 'id',
            name: 'id',
            options: {
                display:false,
                filter: false
            }
        },
        {
            label: 'Name',
            name: 'name',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Surname',
            name: 'lastName',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'LBO',
            name: 'lbo',
            options: {
                filter: true,
                sort: false,
            }
        }
    ]

    const options = {
        selectableRows: "none",
        onRowClick: listenerHandler,
        viewColumns: false,
    };

    const handleClose = () => {
        setOpen(false);
    }
    const handleAppointments = () => {
        setOpen(false);
        const id = localStorage.getItem("PATIENT_ID");
        localStorage.setItem("EDIT_ALLOW", "true")
        history.push({
            pathname: '/patient/appointments',
            search: '?id=' + id,
            state: { detail: id}
        });
    };

    const handleMedicalHistory  = () => {
        setOpen(false);
        const id = localStorage.getItem("PATIENT_ID");
        localStorage.setItem("EDIT_ALLOW", "false")
        history.push({
            pathname: '/patient/history',
            search: '?id=' + id,
            state: { detail: id}
        });
    };

    return (
        <>
            <div id="mojaTabela">
                <MUIDataTable
                    title={"Patients List"}
                    data={patients}
                    columns={columns}
                    options={options}
                />
                <Dialog maxWidth open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                    <DialogContent>
                        <h3>What do you want to view?</h3>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={handleAppointments} color="primary">
                            Appointments
                        </Button>
                        <Button onClick={handleMedicalHistory} color="primary">
                            Medical history
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </>
    );
}

export default DoctorPatientsTable;