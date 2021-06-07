import AxiosClient from "./clients/AxiosClient";

export const PatientService = {
    get,
    update,
    create
};


async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/patientBooks/${id}`);
}

async function update(id, patient_book) {
    return await AxiosClient.put(`https://localhost:8080/patientBooks/${id}`, patient_book);
}

async function create(patient_book) {
    return await AxiosClient.post("https://localhost:8080/patientBooks", patient_book);
}