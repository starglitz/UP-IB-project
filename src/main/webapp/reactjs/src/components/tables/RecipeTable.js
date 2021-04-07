import React, {useEffect, useState} from 'react';
import MUIDataTable from "mui-datatables";

export const RecipeTable = () => {

    const [requests, setRequests] = useState([])
    const [hasError, setError] = useState(false)

    useEffect(() => {
        fetchData()
            .then(res => setRequests(res))
            .catch(err => setError(err));
    },[])

    async function fetchData() {
        const res = await fetch('http://localhost:8080/recipes');
        return res.json()
    }

    console.log(requests)
    const columns = [
        {
            label: 'Recipe ID',
            name: 'recipeId',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Validated',
            name: 'validated',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Last Name',
            name: 'lastName',
            options: {
                filter: true,
                sort: true,
            }
        },
        {
            label: 'Date of issue',
            name: 'issueDate',
            options: {
                filter: true,
                sort: false,
            }
        },
        {
            label: 'Description',
            name: 'description',
            options: {
                filter: true,
                sort: false,
            }
        },
        {
            label: 'Nurse ID',
            name: 'nurse',
            options: {
                filter: true,
                sort: true,
            }
        }]

    const options = {
        selectableRows: false
    };

    return (
        <>
            <MUIDataTable
                title={"Recipe List"}
                data={requests}
                columns={columns}
                options={options}
            />
        </>
    );
}