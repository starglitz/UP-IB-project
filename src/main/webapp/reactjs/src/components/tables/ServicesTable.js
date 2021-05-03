import React, {useEffect, useState} from "react";
import AppointmentRow from "../AppointmentRow";

const ServicesTable = (props) => {

    const [services, setServices] = useState([]);
    const [hasError, setErrors] =  useState(false);

    async function fetchData() {
        const res = await fetch("http://localhost:8080/services/clinic/1");
        res
            .json()
            .then(res => setServices(res))
            .catch(err => setErrors(err));
    }

    useEffect(() => {
        fetchData();
    }, []);



    return (
            <>
                <h1> Clinic menu</h1>
                <table className="styled-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    {services.map((s) =>
                        <tr>
                            <td>{s.service_id}</td>
                            <td>{s.name}</td>
                            <td>{s.price}</td>
                        </tr>

                    )}
                    </tbody>
                </table>
            </>
    );
}

export default ServicesTable;