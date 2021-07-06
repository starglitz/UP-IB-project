import {AuthenticationService} from "./AuthenticationService";
import {UserService} from "./UserService";

const PasswordlessWithLink = () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    const jwt = urlParams.get('token')
    console.log(jwt)
    console.log("???")

    const status = AuthenticationService.loginViaLink(jwt)



    return (
        <>
            <h3> Redirecting, please wait.. </h3>
        </>
    )
}
export default PasswordlessWithLink;