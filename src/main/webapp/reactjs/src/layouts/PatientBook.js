import React, {useEffect, useState} from 'react';
import {PatientBookService} from "../services/PatientBookService";
import MUIDataTable from "mui-datatables";
import {Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {TokenService} from "../services/TokenService";
import {UserService} from "../services/UserService";

function PatientBook() {

    const [requests, setRequests] = useState([])
    const [drugs, setDrugs] = useState([])
    const [illnesses, setIllnesses] = useState([])

    const [hasError, setError] = useState(false)



    useEffect(() => {
        fetchData()
            .catch(err => setError(err));
    }, [])

    async function fetchData() {
        try {
            const decoded_token = TokenService.decodeToken(TokenService.getToken().sub());
            const user = await UserService.getByEmail(decoded_token.sub)
            const response = await PatientBookService.getByPatient(user.data.id)
            setRequests(response.data)
            setDrugs(response.data.drugs)
            setIllnesses(response.data.illnessHistory)

            const drugsData = [];
            response.data.drugs.forEach(function (key) {
                const drug = {drugName: key}
                drugsData.push(drug)
            })
            setDrugs(drugsData)

            const illnessData = [];
            response.data.illnessHistory.forEach(function (key) {
                const illness = {illnessName: key}
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
            options: {
                filter: false,
                customBodyRender: (value, tableMeta, update) => {
                    let rowIndex = Number(tableMeta.rowIndex) + 1;
                    return (<span>{rowIndex}</span>)
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
            options: {
                filter: false,
                customBodyRender: (value, tableMeta, update) => {
                    let rowIndex = Number(tableMeta.rowIndex) + 1;
                    return (<span>{rowIndex}</span>)
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



    return (

        <div className='healthCard'>
            <h1>My Health card</h1>
                <div id="illnessTable">
                <MUIDataTable
                    title={"All my illnesses"}
                    data={illnesses}
                    columns={columnIllnesses}
                />
                </div>
                <div id="drugsTable">
                <MUIDataTable
                    title={"All my drugs"}
                    data={drugs}
                    columns={columnDrugs}
                />
                </div>

        </div>
    );
}

export default PatientBook;