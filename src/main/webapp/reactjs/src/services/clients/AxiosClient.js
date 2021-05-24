import axios from "axios";
import { TokenService } from "../TokenService";
import { AuthenticationService } from "../AuthenticationService";


const AxiosClient = axios.create({
    baseURL: `${process.env.REACT_APP_SPRINTS_BACKEND_SERVER}`,
});

AxiosClient.interceptors.request.use(function success(config) {
    const token = TokenService.getToken();
    if (token) {
        if (TokenService.didTokenExpire()) {
            alert("Token je istekao");
            AuthenticationService.logout();
            return false;
        }
        config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
});

AxiosClient.interceptors.response.use(
    function success(response) {
        return response;
    },
    function failure(error) {
        const token = TokenService.getToken();
        if (token) {
            if (error.response && error.response.status === 403) {
                AuthenticationService.logout();
            }
        }
        throw error;
    }
);

export default AxiosClient;