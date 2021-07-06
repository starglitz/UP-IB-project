import React, {useEffect, useState} from 'react';
import {DoctorService} from "../../services/DoctorService";
import {Typography} from "@material-ui/core";
import MUIDataTable from "mui-datatables";

const DoctorsTable = ({clinic_id}) => {

    const [doctors, setDoctors] = useState([]);


    useEffect(() => {
        fetchDoctors()

    }, [clinic_id])


    async function fetchDoctors() {
        try {
            const response = await DoctorService.getByClinicId(clinic_id)
            setDoctors(response.data)
            console.log(response.data);
        } catch (error) {
            console.error(`Error loading doctors !: ${error}`);
        }
    }





    const columns = [
        {
            label: 'ID',
            name: 'id',
            options: {
                display: false,
                filter: false,
                sort: true,
            }
        },
        {
            label: 'Name',
            name: 'user',
            options: {
                filter: true,
                sort: true,
                customBodyRender: (value, tableMeta, updateValue) => (
                    <Typography>
                        {value.name}
                    </Typography>
                )
            }
        },
        {
            label: 'Lastname',
            name: 'user',
            options: {
                filter: true,
                sort: true,
                customBodyRender: (value, tableMeta, updateValue) => (
                    <Typography>
                        {value.name}
                    </Typography>
                )
            }
        },
        {
            label: 'Grade',
            name: 'grade',
            options: {
                filter: true,
                sort: true,
            }
        }]

    const options = {
        selectableRows: 'none',
        viewColumns: false,
    };

    return (
        <>
            <div id="mojaTabela">
                <MUIDataTable
                    title={"Doctors List"}
                    data={doctors}
                    columns={columns}
                    options={options}
                />
            </div>
        </>
    );
}

export default DoctorsTable;