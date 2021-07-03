import React, {useEffect, useState} from 'react';
import MUIDataTable from "mui-datatables";
import {AppointmentService} from "../../services/AppointmentService";
import {PatientService} from "../../services/PatientService";
import {PatientBookService} from "../../services/PatientBookService";
import {keys} from "@material-ui/core/styles/createBreakpoints";

export const PatientIllnessTable = () => {

    const [requests, setRequests] = useState([])
    const [drugs, setDrugs] = useState([])
    const [illnesses, setIllnesses] = useState([])
    const [hasError, setError] = useState(false)

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

    const options = {
        selectableRows: false,
    };

    return (
        <div className='flex-container' style={{margin: "2% 10% 2% 10%"} }>
            <MUIDataTable
                className='flex-child'
                title={"Past illnesses"}
                data={illnesses}
                columns={columnIllnesses}
                options={options}
            />
            <MUIDataTable
                className='flex-child'
                style={{marginTop: "5%"}}
                title={"Given drugs"}
                data={drugs}
                columns={columnDrugs}
                options={options}
            />
        </div>
    );
}