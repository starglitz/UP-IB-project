import {AuthenticationService} from "./AuthenticationService";


const PasswordLess = () => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    const jwt = urlParams.get('token')
    console.log(jwt)

    let message = "Successfully logged in with password-less !"
    const status = AuthenticationService.checkTokenValidity(jwt)
    console.log(status.status)

    // if (status == 404) {
    //     message = "This link is not valid anymore :("
    // }

    return(
        <>
            <h1> This link is not valid anymore </h1>
        </>
    )
}

export default PasswordLess