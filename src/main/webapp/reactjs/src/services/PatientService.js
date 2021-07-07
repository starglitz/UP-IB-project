import AxiosClient from "./clients/AxiosClient";

export const PatientService = {
    get,
    getAll,
    update,
    create,
    getByCurrentDoctor,
    getLogged
};


async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/patients/${id}`);
}

async function getLogged() {
    return await AxiosClient.get(`https://localhost:8080/patients/patient`);
}


async function getAll() {
    return await AxiosClient.get(`https://localhost:8080/patients`);
}

async function update(id, patient) {
    return await AxiosClient.put(`https://localhost:8080/patients/${id}`, patient);
}

async function create(patient) {
    return await AxiosClient.post("https://localhost:8080/patients", patient);
}

async function getByCurrentDoctor() {
    return await AxiosClient.get(`https://localhost:8080/patients/doctor`);
}