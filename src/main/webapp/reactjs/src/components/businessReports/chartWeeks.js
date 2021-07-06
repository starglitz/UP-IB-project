import React, {useEffect, useState} from 'react';
import {Bar} from "react-chartjs-2";
import {AppointmentService} from "../../services/AppointmentService";

const ChartWeeks = ({clinic_id}) => {

    const [weeks, setWeeks] = useState( {})

    useEffect(() => {
        fetchData()

    }, [clinic_id]);

    async function fetchData() {
        try {
            const response = await AppointmentService.countAppointmentsByWeeks(clinic_id);
            setWeeks(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }
    const labels = () =>{
        let lista = [];
        for (let i = 1; i < 53; i++) {
            lista.push('Week ' + i);
        }
        return lista;
    }

    const dataWeeks = () =>{
        let lista = [];
        for (let i = 1; i < 53; i++) {
            lista.push(weeks[i]);
        }
        return lista;
    }

    const data = {
        chartData:{
            labels: labels(),
            datasets:[

                {
                    label:'appointments',
                    data:dataWeeks(),
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

export default ChartWeeks;