import AxiosClient from "./clients/AxiosClient";

export const RegisterRequestService = {
    getAll,
    update
};



async function getAll() {
    return await AxiosClient.get(`https://localhost:8080/registerRequests`);
}

async function update(id, request) {
    return await AxiosClient.put(`https://localhost:8080/registerRequests/${id}`, request);
}
