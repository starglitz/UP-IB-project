import React, {useEffect, useState} from 'react';
import {Bar} from "react-chartjs-2";
import {AppointmentService} from "../../services/AppointmentService";

const ChartDays = ({clinic_id, month}) => {

    const [days, setDays] = useState([])

    useEffect(() => {
        fetchData()

    }, [month]);

    async function fetchData() {
        try {
            const response = await AppointmentService.countAppointmentsByDays(clinic_id,month);
            setDays(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }
    const labels = () =>{
        let lista = [];
        if(month == "April" || month == "June" || month == "September" || month == "November") {
            for (let i = 1; i < 31; i++) {
                lista.push("Day " + i);
            }
        }
        else if(month == "February"){
            for (let i = 1; i < 29; i++) {
                lista.push("Day " + i);
            }
        }
        else{
            for (let i = 1; i < 32; i++) {
                lista.push("Day " + i);
            }
        }
        return lista;
    }

    const dataDays = () =>{
        let lista = [];
        for (let i = 1; i < 32; i++) {
            lista.push(days[i]);
        }
        return lista;
    }

    const data = {
        chartData:{
            labels: labels(),
            datasets:[

                {
                    label:'appointments',
                    data:dataDays(),
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ]
                }
            ]
        }
    }
    return (
        <div>
            <Bar

                data={data.chartData}

            />

        </div>
    );
}

export default ChartDays;