import {AuthenticationService} from "./AuthenticationService";
import {UserService} from "./UserService";

const PasswordlessWithLink = () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    const jwt = urlParams.get('token')
    console.log(jwt)

    const status = AuthenticationService.loginViaLink(jwt)



    return (
        <>
            <h1> Success </h1>
        </>
    )
}
export default PasswordlessWithLink;