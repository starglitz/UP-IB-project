import {Button} from "@material-ui/core";

const RegisterRequestRow = (props) => {


if(props.status === 'PENDING') {
    return (
        <tr>
            <td>{props.id}</td>
            <td>{props.name}</td>
            <td>{props.status}</td>
            <td>
                <Button id="accept" className="accept" size="small" variant="contained" onClick={() => props.accept(props.id, props.patientid)}>Accept</Button>
            </td>
            <td>
                <Button id="decline" className="accept" size="small" variant="contained" color="secondary"
                        onClick={() => props.decline(props.id, props.patientid)}>Decline</Button>
            </td>

        </tr>
    )
}
else {
    return (null)
}
}

export default RegisterRequestRow;