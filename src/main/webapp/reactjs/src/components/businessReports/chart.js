import React, {useEffect, useState} from 'react';
import {Bar} from "react-chartjs-2";
import {AppointmentService} from "../../services/AppointmentService";
import ChartDays from "./chartDays";

const Chart = ({clinic_id}) => {

    const [months, setMonths] = useState( {
        "JANUARY": 0,
        "JUNE": 0,
        "MAY": 0,
        "OCTOBER": 0,
        "DECEMBER": 0,
        "MARCH": 0,
        "FEBRUARY": 0,
        "AUGUST": 0,
        "JULY": 0,
        "SEPTEMBER": 0,
        "NOVEMBER": 0,
        "APRIL": 0
    })

    const[month, setMonnth] = useState(null)

    useEffect(() => {
        fetchData()

    }, [clinic_id]);

    async function fetchData() {
        try {
            const response = await AppointmentService.countAppointmentsByMonths(clinic_id);
            setMonths(response.data)
        } catch (error) {
            console.error(`Error loading appointments !: ${error}`);
        }
    }

    const handleClick = (e) =>{
            setMonnth(e.chart.tooltip.title[0])
    }

const data = {
    chartData:{
        labels: ['January',
            'February',
            'March',
            'April',
            'May',
            'June',
            'July',
            'August',
            'September',
            'October',
            'November',
            'December'],
        datasets:[

            {
                label:'appointments',
                data:[
                    months.JANUARY,
                    months.FEBRUARY,
                    months.MARCH,
                    months.APRIL,
                    months.MAY,
                    months.JUNE,
                    months.JULY,
                    months.AUGUST,
                    months.SEPTEMBER,
                    months.OCTOBER,
                    months.NOVEMBER,
                    months.DECEMBER
                ],
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
                options={{ onClick: handleClick}}

          />
            <ChartDays clinic_id = {clinic_id} month={month}/>
        </div>
    );
}

export default Chart;