import React, {useEffect, useState} from 'react';
import TextField from "@material-ui/core/TextField";
import {Tooltip} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import {AppointmentService} from "../../services/AppointmentService";

const IncomeComponent = ({clinic_id}) => {

    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
    }

    const [dateFrom, setDateFrom] = useState(formatDate('Sun Jan 01,2000'));
    const [dateTo, setDateTo] = useState(formatDate('Sun Jan 01,2000'));
    const [income, setIncome] = useState(0);
    const [clinicId, setClinicId] = useState(0)

    useEffect(() => {
        setClinicId(clinic_id)

    }, [clinic_id]);

    const dateFromPickHandler = (e) => {
        setDateFrom(e);

    }

    const dateToPickHandler = (e) => {
        setDateTo(e);

    }

    const clickHandler = () => {
       fetchData()
    }


    async function fetchData() {
        try {
            const response = await AppointmentService.getIncomeBetweenDates(clinic_id,dateFrom, dateTo);
            setIncome(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }

    return (
        <div className="income-container">
            <div className="income-dataPickers">
                <h3>Income in specific period</h3>

                <TextField
                    id="dateFrom"
                    label="From Date"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    onChange={(event) => dateFromPickHandler(event.target.value)}
                />
                <TextField
                    id="dateTo"
                    label="To Date"
                    type="date"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    onChange={(event) => dateToPickHandler(event.target.value)}
                />
            </div>
            <div className="income-tF-button">
                <div className="income-field">
                <TextField
                    id="income"
                    label="Income"
                    type="number"
                    value={income}
                    InputLabelProps={{
                        shrink: true,
                    }}
                    disabled
                />
                </div>
                <Button type='submit' variant="contained" size="large" color="default" onClick={clickHandler}>Show</Button>

            </div>
        </div>
    );
}

export default IncomeComponent;