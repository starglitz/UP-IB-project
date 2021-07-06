import AxiosClient from "./clients/AxiosClient";

export const PatientBookService = {
    get,
    getByPatient,
    update,
    create,
    updateIllnesses,
    updateDrugs,
    getLoggedPatient
};


async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/patientBooks/${id}`);
}

async function getByPatient(id) {
    return await AxiosClient.get(`https://localhost:8080/patientBooks/patient/${id}`);
}

async function getLoggedPatient() {
    return await AxiosClient.get(`https://localhost:8080/patientBooks/patient/healthCard`);
}

async function update(id, patient_book) {
    return await AxiosClient.put(`https://localhost:8080/patientBooks/${id}`, patient_book);
}

async function create(patient_book) {
    return await AxiosClient.post("https://localhost:8080/patientBooks", patient_book);
}

async function updateIllnesses(id, illnessChange) {
    return await AxiosClient.put(`https://localhost:8080/patientBooks/illnesses/${id}`, illnessChange);
}

async function updateDrugs(id, drugChange) {
    return await AxiosClient.put(`https://localhost:8080/patientBooks/drugs/${id}`, drugChange);
}