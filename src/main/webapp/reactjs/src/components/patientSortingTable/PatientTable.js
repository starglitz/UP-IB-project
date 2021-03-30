import 'bootstrap/dist/css/bootstrap.min.css'
import ReactBootstrapTable from 'react-bootstrap/Table'
import React, {Component} from "react";
import '../../index.css'
import {SortingTable} from "./SortingTable";

export class PatientTable extends Component {

    state = {
        loading: true,
        patients: null,
    }

    async componentDidMount() {
        const url = "http://localhost:8080/patients"
        const response = await fetch(url)
        const data = await response.json()
        this.setState({loading: false, patients: data})
        console.log(data)
    }

    render() {
        if (this.state.loading) {
            return <div style={{textAlign: 'center'}}>loading...</div>;
        }

        const patientsData = this.state.patients

        const renderPatient = (patientData, index) => {
            return (
                <tr key={index}>
                    <td>{patientData.id}</td>
                    <td>{patientData.name}</td>
                    <td>{patientData.lastName}</td>
                    <td>{patientData.email}</td>
                    <td>{patientData.phoneNumber}</td>
                    <td>{patientData.address}</td>
                    <td>{patientData.city}</td>
                    <td>{patientData.country}</td>
                    <td>{patientData.lbo}</td>
                </tr>
            )
        }

        return (
            <div className={"content-box"}>
                <ReactBootstrapTable striped bordered hover size="sm" variant="dark">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>Country</th>
                        <th>LBO</th>
                    </tr>
                    </thead>
                    <tbody>
                        {patientsData.map(renderPatient)}
                    </tbody>
                </ReactBootstrapTable>


                <SortingTable/>
            </div>
        )
    }

}