import 'bootstrap/dist/css/bootstrap.min.css'
import React, {Component} from "react";
import '../../index.css'

class data extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isFetching: false,
            users: []
        };
    }

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

    patientData = this.state.patients
}