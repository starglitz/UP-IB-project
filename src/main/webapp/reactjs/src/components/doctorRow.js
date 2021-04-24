import {Button} from "@material-ui/core";
import React from "react";


const doctorRow = (props) => {

    return (
        <tr>
            <td>{props.id}</td>
            <td>{props.name}</td>
            <td>{props.email}</td>
            <td>{props.phoneNumber}</td>
        </tr>
    )


}

export default doctorRow;