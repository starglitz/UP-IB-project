import {Button} from "@material-ui/core";
import React from "react";

const AppointmentRow = (props) => {



     if(props.deleted === false) {

         if (props.status !== "RESERVED") {

             return (
                 <tr>
                     <td>{props.id}</td>
                     <td>{props.status}</td>
                     <td>{props.date}</td>
                     <td>{props.start}</td>
                     <td>{props.end}</td>
                     <td>{props.doctor.name + " " + props.doctor.lastName}</td>
                     <td>{props.nurse.name + " " + props.nurse.lastName}</td>
                     <td>{props.price}</td>
                     <td>
                         <Button id="accept" className="accept" size="small" variant="contained"
                                 onClick={() => props.updateAppointment(props.id)}>Modify</Button>
                     </td>
                     <td>
                         <Button id="delete" className="accept" size="small" variant="contained" color="secondary"
                                 onClick={() => props.deleteAppointment(props.id)}>Delete</Button>
                     </td>

                 </tr>
             )
         }
         else {
             return (
                 <tr>
                     <td>{props.id}</td>
                     <td>{props.status}</td>
                     <td>{props.date}</td>
                     <td>{props.start}</td>
                     <td>{props.end}</td>
                     <td>{props.doctor.name + " " + props.doctor.lastName}</td>
                     <td>{props.nurse.name + " " + props.nurse.lastName}</td>
                     <td>{props.price}</td>
                     <td colSpan={2}>Reserved appointments can't be modified or deleted</td>


                 </tr>
             )
         }
     }
    else {
        return (null)
    }
}

export default AppointmentRow;