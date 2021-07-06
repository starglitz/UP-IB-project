import axios from "axios";
import { TokenService } from "../TokenService";
import { AuthenticationService } from "../AuthenticationService";
import {common} from "@material-ui/core/colors";


const AxiosClient = axios.create({
    baseURL: `${process.env.REACT_APP_SPRINTS_BACKEND_SERVER}`,
});

AxiosClient.interceptors.request.use(function success(config) {
    const token = TokenService.getToken();
    const refreshToken = TokenService.getRefreshToken();
    console.log(refreshToken)
    console.log(token)
    if (token && refreshToken) {
        if(TokenService.didTokenExpire(token) && TokenService.didTokenExpire(refreshToken)){
            console.log('Refresh token je istekao')
            AuthenticationService.logout()
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
        const originalRequest = error.config;
        const token = TokenService.getToken();
        if (token) {
            if (error.response.status === 401 && !originalRequest._retry) {
                console.log("Token je istekao, pokusavam refresh")
                originalRequest._retry = true;
                const refreshToken = TokenService.getRefreshToken();
                return axios.post('https://localhost:8080/auth/refresh',
                    {}, {  headers: {
                            'Authorization': `Bearer ${refreshToken}`
                        }})
                    .then(res => {
                        if (res.status === 200) {
                            TokenService.setToken(res.data);
                            console.log('Token je refresovan, ponavljam request')
                            error.config.headers['Authorization'] = 'Bearer ' + TokenService.getToken();
                            error.config.baseURL = undefined;
                            return axios(originalRequest);
                        }

                    })
            }
        }

        throw error;
    }
);

export default AxiosClient;