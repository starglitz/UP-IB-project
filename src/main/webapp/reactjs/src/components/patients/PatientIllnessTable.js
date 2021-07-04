import React, {useEffect, useState} from 'react';
import MUIDataTable from "mui-datatables";
import {AppointmentService} from "../../services/AppointmentService";
import {PatientService} from "../../services/PatientService";
import {PatientBookService} from "../../services/PatientBookService";
import {keys} from "@material-ui/core/styles/createBreakpoints";
import {Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";

export const PatientIllnessTable = () => {

    const [requests, setRequests] = useState([])
    const [drugs, setDrugs] = useState([])
    const [illnesses, setIllnesses] = useState([])

    const [hasError, setError] = useState(false)

    const [pastIllness, setPastIllness] = React.useState('')
    const [newIllness, setNewIllness] = React.useState('')
    const [illnessOpen, setIllnessOpen] = React.useState(false);
    const editedIllness = document.getElementById('illnessTf')

    const [pastDrug, setPastDrug] = React.useState('')
    const [newDrug, setNewDrug] = React.useState('')
    const [drugOpen, setDrugOpen] = React.useState(false);
    const editedDrug = document.getElementById('drugTf')

    useEffect(() => {
        fetchData()
            .catch(err => setError(err));
    },[])

    async function fetchData() {
        try {
            const response = await PatientBookService.getByPatient(localStorage.getItem("PATIENT_ID"))
            setRequests(response.data)
            setDrugs(response.data.drugs)
            setIllnesses(response.data.illnessHistory)

            const drugsData = [];
            response.data.drugs.forEach(function (key) {
                const drug = { drugName: key }
                drugsData.push(drug)
            })
            setDrugs(drugsData)

            const illnessData = [];
            response.data.illnessHistory.forEach(function (key) {
                const illness = { illnessName: key }
                illnessData.push(illness)
            })
            setIllnesses(illnessData)

        } catch (error) {
            console.error(`Error loading patients !: ${error}`);
        }
    }

    console.log(requests)
    console.log(drugs)
    console.log(illnesses)

    const columnIllnesses = [
        {
            name: 'No.',
            label: '',
            options: {filter: false,
                customBodyRender : (value, tableMeta, update) => {
                    let rowIndex = Number(tableMeta.rowIndex) + 1;
                    return ( <span>{rowIndex}</span> )
                }
            },
        },
        {
            label: 'Illness',
                name: 'illnessName',
            options: {
            filter: true,
                sort: true,
        }
        },
    ]
    const columnDrugs = [
        {
            name: 'No.',
            label: '',
            options: {filter: false,
                customBodyRender : (value, tableMeta, update) => {
                    let rowIndex = Number(tableMeta.rowIndex) + 1;
                    return ( <span>{rowIndex}</span> )
                }
            },
        },
        {
            label: 'Drug name',
            name: 'drugName',
            options: {
                filter: true,
                sort: true,
            }
        },
    ]

    function handleClose() {
        setDrugOpen(false)
        setIllnessOpen(false)
    }

    function setIllnessEditClose() {
        setIllnessOpen(false)
    }

    const illnessHandler = (e) => {
        console.log(e)
        if (localStorage.getItem('EDIT_ALLOW') === 'true'){
            setPastIllness(e[1])
            setNewIllness(e[1])
            setIllnessOpen(true);
        }
    }

    function handleIllnessChange() {

        const illnessChanged = {
            doctorId: localStorage.getItem("DOCTOR_ID"),
            oldIllness: pastIllness,
            newIllness: newIllness
        };

        const status = PatientBookService.updateIllnesses(requests.id, illnessChanged)
            .then((response) => response.data)
            .catch(error => {
                setError(error)
                alert("You cant edit that illness because you didnt give it out")
            })
        // window.location.reload()
    }

    const changeInputHandlerIllness = (event) => {
        const illnessChanged = event.target.value;
        setNewIllness(illnessChanged)
    }

    const illnessesOptions = {
        selectableRows: 'none',
        onRowClick: illnessHandler,
    };

    function setDrugEditClose() {
        setDrugOpen(false)
    }

    const drugHandler = (e) => {
        if (localStorage.getItem('EDIT_ALLOW') === 'true') {
            setPastDrug(e[1])
            setNewDrug(e[1])
            setDrugOpen(true);
        }
    }

    function handleDrugChange() {
        const drugChange = {
            oldDrug: pastDrug,
            newDrug: editedDrug.value
        };
        console.log(drugChange)
        PatientBookService.updateDrugs(requests.id, drugChange).catch(err => setError(err))
            .then(response => console.log(response.data))
            .catch(err => setError(err))
        window.location.reload()
    }

    const drugsOptions = {
        selectableRows: 'none',
        onRowClick: drugHandler,
    };

    const changeInputHandlerDrug = (event) => {
        const drugChanged = event.target.value;
        setNewDrug(drugChanged)
    }

    return (
        <div className='flex-container' style={{margin: "2% 10% 2% 10%"} }>

            {/* Past illnesses table */}
            <MUIDataTable
                className='flex-child'
                title={"Edit past illnesses YOU have given"}
                data={illnesses}
                columns={columnIllnesses}
                options={illnessesOptions}
            />
            {/* Editing illnesses dialog */}
            <Dialog  maxWidth open={illnessOpen} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Edit given illness</DialogTitle>
                <DialogContent>
                    <TextField
                        value={newIllness}
                        onChange={(event) =>
                            changeInputHandlerIllness(event)}
                        fullWidth
                        autoFocus
                        margin="dense"
                        id="illnessTf"
                        label="Illness"
                        type="text"
                        variant="outlined"
                    />
                    <br />
                </DialogContent>
                <DialogActions>
                    <Button fullWidth onClick={setIllnessEditClose} color="primary">
                        Cancel
                    </Button>
                    <Button fullWidth onClick={handleIllnessChange} color="primary">
                        Proceed
                    </Button>
                </DialogActions>
            </Dialog>


            {/* Past drugs table */}
            <MUIDataTable
                className='flex-child'
                style={{marginTop: "5%"}}
                title={"Edit given drugs"}
                data={drugs}
                columns={columnDrugs}
                options={drugsOptions}
            />
            {/* Editing drugs dialog */}
            <Dialog  maxWidth open={drugOpen} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Edit given drug</DialogTitle>
                <DialogContent>
                    <TextField
                        value={newDrug}
                        onChange={(event) =>
                            changeInputHandlerDrug(event)}
                        fullWidth
                        autoFocus
                        margin="dense"
                        id="drugTf"
                        label="Drug"
                        type="text"
                        variant="outlined"
                    />
                    <br />
                </DialogContent>
                <DialogActions>
                    <Button fullWidth onClick={setDrugEditClose} color="primary">
                        Cancel
                    </Button>
                    <Button fullWidth onClick={handleDrugChange} color="primary">
                        Proceed
                    </Button>
                </DialogActions>
            </Dialog>

        </div>
    );
}