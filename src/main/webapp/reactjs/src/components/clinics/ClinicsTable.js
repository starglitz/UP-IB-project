import React, {useEffect, useState} from 'react';
import MUIDataTable, { TableFilterList }  from "mui-datatables";
import {useHistory} from "react-router-dom";

function ClinicsTable() {



    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)
    const history = useHistory();

    useEffect(() => {
        fetchData()
            .then(res => setRequests(res))
            .catch(err => setError(err));
    },[])

    async function fetchData() {
        const res = await fetch('http://localhost:8080/allClinics',);
        return res.json()
    }

    const clickHandler = (e) => {
        const id = e[0];
        history.push("/clinic/" + id)
    }
    console.log(requests)
    const columns = [
        {
            label: 'id',
            name: 'clinic_id',
            options: {
                display:false
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
            label: 'Descriptionn',
            name: 'description',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Rating',
            name: 'rating',
            options: {
                filter: true,
                sort: false,
            }
        }]

    const options = {
        selectableRows: false,
        onRowClick: clickHandler

    };

    return (
        <>
            <div id="mojaTabela">
            <MUIDataTable
                title={"Clinic List"}
                data={requests}
                columns={columns}
                options={options}
            />
            </div>
        </>
    );
}

export default ClinicsTable;