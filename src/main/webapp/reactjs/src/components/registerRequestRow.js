import {Button} from "@material-ui/core";

const RegisterRequestRow = (props) => {

      let accept = () => {
          let request = {status:"APPROVED", register_request_id:props.id};
          sendData(request);
      }

        let decline = () => {
            let request = {status:"DECLINED", register_request_id:props.id};
            sendData(request);
        }

        const sendData = (data) => {
            fetch('http://localhost:8080/updateRequest', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            })
                .then(response => response)
                .then(data => {
                    console.log('Success:', data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });

        }


if(props.status == 'PENDING') {
    return (
        <tr>
            <td>{props.patientid}</td>
            <td>{props.email}</td>
            <td>{props.name}</td>
            <td>
                <Button id="accept" className="accept" size="small" variant="contained" onClick={accept}>Accept</Button>
            </td>
            <td>
                <Button id="decline" className="accept" size="small" variant="contained" color="secondary"
                        onClick={decline}>Decline</Button>
            </td>

        </tr>
    )
}
else {
    return (null)
}
}

export default RegisterRequestRow;