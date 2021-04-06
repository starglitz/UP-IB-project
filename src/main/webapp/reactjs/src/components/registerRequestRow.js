import {Button} from "@material-ui/core";

const RegisterRequestRow = (props) => {
      let accept = () => {

      }

        let decline = () => {

        }

return (
    <tr>
        <td>{props.patientid}</td>
        <td>{props.email}</td>
        <td>{props.name}</td>
        <td>
                <Button className="accept" size="small" variant="contained" onClick={accept}>Accept</Button>
        </td>
            <td>
                    <Button className="accept" size="small" variant="contained" color="secondary" onClick={decline}>Decline</Button>
            </td>

    </tr>
)
}

export default RegisterRequestRow;