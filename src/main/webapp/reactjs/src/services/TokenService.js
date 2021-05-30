import jwtDecode from "jwt-decode";

export const TokenService = {
    getToken,
    setToken,
    removeToken,
    decodeToken,
    didTokenExpire,
    getRefreshToken,
    setRefreshToken,
};

function getToken() {
    return localStorage.getItem("token");
}

function getRefreshToken() {
    return localStorage.getItem("refreshToken");
}

function setToken(value) {
    localStorage.setItem("token", value);
}

function setRefreshToken(value){
    localStorage.setItem("refreshToken", value)
}

function removeToken() {
    localStorage.removeItem("token");
    localStorage.removeItem("refreshToken")
}

function decodeToken(token) {
    try {
        return jwtDecode(token);
    } catch (error) {
        return null;
    }
}

function didTokenExpire(toke) {
    const token = toke;
    const decodedToken = token ? decodeToken(token) : null;
    const expDate = new Date (decodedToken.exp * 1000)
    return decodedToken ? expDate < Date.now() : null;
}
