import AxiosClient from "./clients/AxiosClient";

export const UserService = {
    edit,
    getMyInfo
};

async function edit(id, user) {
    let status = '200';
    await AxiosClient.put(`http://localhost:8080/user/${id}`, user)
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
    return await AxiosClient.get("http://localhost:8080/user/profile");
}