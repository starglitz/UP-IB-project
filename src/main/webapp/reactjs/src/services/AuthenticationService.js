import AxiosClient from "./clients/AxiosClient";
import { TokenService } from "../services/TokenService";

export const AuthenticationService = {
    login,
    logout,
    getRole,
    findCommonElement
};

async function login(userCredentials) {

    console.log(userCredentials)
    let status = "200";
    const response = await AxiosClient.post(
        "http://localhost:8080/auth/login",
        userCredentials
    ).catch(function (error) {
        if (error.response) {
            if(error.response.status == '404') {
                status = '404'
                return '404'
            }
            else if(error.response.status == '403') {
                status = '403'
                return '403'
            }

        }
    });


    if(status != '403' && status != '404') {
        console.log('proslo')
        const decoded_token = TokenService.decodeToken(response.data);
        if (decoded_token) {
            TokenService.setToken(response.data);
            window.location.assign("/");
        } else {
            console.error("Invalid token");

        }
    }


    return status;

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
        console.log(roles)
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
