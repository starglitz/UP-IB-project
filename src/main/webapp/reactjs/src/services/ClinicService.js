import AxiosClient from "./clients/AxiosClient";

export const ClinicService = {
    get,
    getAll,
    update,
    create,
    getByDate,
    getByLoggedInAdmin,
    getNotRatedByPatient,
    rate
};


async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/clinics/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`https://localhost:8080/clinics`);
}

async function update(id, clinic) {
    return await AxiosClient.put(`https://localhost:8080/clinics/${id}`, clinic);
}

async function create(clinic) {
    return await AxiosClient.post("https://localhost:8080/clinics", clinic);
}

async function getByDate(date) {
    return await AxiosClient.get(`https://localhost:8080/clinics/date/${date}`);
}

async function getByLoggedInAdmin() {
    return await AxiosClient.get(`https://localhost:8080/clinics/admin`);
}

async function getNotRatedByPatient() {
    return await AxiosClient.get(`https://localhost:8080/clinics/not_rated`);
}

async function rate(id, rating) {
    return await AxiosClient.put(`https://localhost:8080/clinics/rate/${id}`, rating);
}