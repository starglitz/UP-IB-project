import {Button} from "@material-ui/core";
import React from "react";

const AppointmentRow = (props) => {



     if(props.deleted === false) {
        return (
            <tr>
                <td>{props.id}</td>
                <td>{props.status}</td>
                <td>{props.date}</td>
                <td>{props.time}</td>
                <td>{props.duration}</td>
                <td>{props.doctor.name + " " + props.doctor.lastName}</td>
                <td>{props.nurse.name + " " + props.nurse.lastName}</td>
                <td>{props.price}</td>
                <td>
                    <Button id="accept" className="accept" size="small" variant="contained" onClick={() => props.accept(props.id)}>Modify</Button>
                </td>
                <td>
                    <Button id="delete" className="accept" size="small" variant="contained" color="secondary"
                            onClick={() => props.deleteAppointment(props.id)}>Delete</Button>
                </td>

            </tr>
        )
    }
    else {
        return (null)
    }
}

export default AppointmentRow;