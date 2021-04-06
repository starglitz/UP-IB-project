import {Button} from "@material-ui/core";

const RegisterRequestRow = (props) => {
return (
    <tr>
        <td>{props.patientid}</td>
        <td>{props.email}</td>
        <td>{props.name}</td>
        <td>
                <Button className="accept" size="small" variant="contained">Accept</Button>
        </td>
            <td>
                    <Button className="accept" size="small" variant="contained" color="secondary">Decline</Button>
            </td>

    </tr>
)
}

export default RegisterRequestRow;