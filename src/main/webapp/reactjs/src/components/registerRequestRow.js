
const RegisterRequestRow = (props) => {
return (
    <tr>
        <td>{props.id}</td>
        <td>{props.email}</td>
        <td>{props.name}</td>
    </tr>
)
}

export default RegisterRequestRow;