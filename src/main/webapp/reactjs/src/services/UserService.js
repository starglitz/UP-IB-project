import AxiosClient from "./clients/AxiosClient";

export const UserService = {
    edit,
    getMyInfo,
    getAll,
    get,
    setEnabled,
    updatePassword,
    getByEmail,
    isFirstTime,
    editProfile
};

async function edit(id, user) {
    let status = '200';
    await AxiosClient.put(`https://localhost:8080/user/${id}`, user)
        .catch(function (error) {
            if (error.response) {
                console.log(error.response.status);
                if(error.response.status == '400') {
                    status = '400'
                    return '400'
                }
            }
        });
    return status;
}

async function getMyInfo() {
    return await AxiosClient.get("https://localhost:8080/user/profile");
}

async function getAll() {
    return await AxiosClient.get("https://localhost:8080/user")
}

async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/user/${id}`);
}

async function getByEmail(email) {
    return await AxiosClient.get(`https://localhost:8080/user/email/${email}`);
}

async function setEnabled(token) {
    await AxiosClient.put(`https://localhost:8080/user/enable/${token}`)

}

async function updatePassword(id, user) {
    let status = '200';
    await AxiosClient.put(`https://localhost:8080/user/passwordUpdate/${id}`, user)
        .catch(function (error) {
            if (error.response) {
                console.log(error.response.status);
                if(error.response.status == '400') {
                    status = '400'
                    return '400'
                }
            }
        });
    return status;
}

async function isFirstTime() {
    return await AxiosClient.get(`https://localhost:8080/user/firstTime`);
}

async function editProfile(id, user) {
    let status = '200';
    await AxiosClient.put(`https://localhost:8080/user/profile/${id}`, user)
        .catch(function (error) {
            if (error.response) {
                console.log(error.response.status);
                if(error.response.status == '400') {
                    status = '400'
                    return '400'
                }
            }
        });
    return status;
}