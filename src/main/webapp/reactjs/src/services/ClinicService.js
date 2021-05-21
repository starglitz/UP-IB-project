import AxiosClient from "./clients/AxiosClient";

export const ClinicService = {
    get,
    getAll,
    update,
    create,
    getByDate
};


async function get(id) {
    return await AxiosClient.get(`http://localhost:8080/clinics/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`http://localhost:8080/clinics`);
}

async function update(id, clinic) {
    return await AxiosClient.put(`http://localhost:8080/clinics/${id}`, clinic);
}

async function create(clinic) {
    return await AxiosClient.post("http://localhost:8080/clinics", clinic);
}

async function getByDate(date) {
    return await AxiosClient.get(`http://localhost:8080/clinics/${date}`);
}