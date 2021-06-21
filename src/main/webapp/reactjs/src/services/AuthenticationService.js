import AxiosClient from "./clients/AxiosClient";
import { TokenService } from "../services/TokenService";

export const AuthenticationService = {
    login,
    logout,
    passwordLessRequest,
    getRole,
    findCommonElement,
    checkTokenValidity
};

async function login(userCredentials) {

    console.log(userCredentials)
    let status = "200";
    const response = await AxiosClient.post(
        "https://localhost:8080/auth/login",
        userCredentials
    ).catch(function (error) {
        if (error.response) {
            if(error.response.status == '404') {
                status = '404'
                return '404'
            }
            else if(error.response.status == '401') {
                status = '401'
                return '401'
            }

        }
    });


    if(status != '401' && status != '404' && response) {
        console.log('proslo')
        TokenService.removeToken()
        const decoded_token = TokenService.decodeToken(response.data.accessToken);
        const decoded_refresh_token = TokenService.decodeToken(response.data["refreshToken"]);
        if (decoded_token && decoded_refresh_token) {
            TokenService.setToken(response.data.accessToken);
            TokenService.setRefreshToken(response.data["refreshToken"]);
            window.location.assign("/");
            console.log(TokenService.getToken(), TokenService.getRefreshToken())
            alert("A")
        } else {
            console.error("Invalid token");

        }
    }


    return status;

}

async function passwordLessRequest(email) {
    let status = "200";
    await AxiosClient.post("https://localhost:8080/auth/passwordless", email).catch(function (error) {
        if (error.response) {
            if(error.response.status == '404') {
                status = '404'
                console.log(status)
                return '404'
            }
            else if(error.response.status == '401') {
                status = '401'
                console.log(status)
                return '401'
            }

        }
    });

    return status
}

async function checkTokenValidity(jwt) {
    const response = await AxiosClient("https://localhost:8080/auth/magic/" + jwt)
    if (response.status == 200) {
        const tokenPair = response.data
        TokenService.setToken(tokenPair.jwt);
        TokenService.setRefreshToken(tokenPair.refreshJwt)
        window.location.assign("/");
    }
}



function logout() {
    TokenService.removeToken();
    window.location.assign("/login");
}

function getRole() {
    const token = TokenService.getToken();
    const decoded_token = token ? TokenService.decodeToken(token) : null;
    if (decoded_token) {
        let string = decoded_token.roles;
        let spliceString = string.slice(1, string.length - 1)
        let roles = spliceString.split(", ")
        return roles;
    } else {
        return null;
    }
}

function findCommonElement(array1, array2) {


    for(let i = 0; i < array1.length; i++) {

        for(let j = 0; j < array2.length; j++) {

            if(array1[i] == array2[j]) {
                return true;
            }
        }
    }

    return false;
}
