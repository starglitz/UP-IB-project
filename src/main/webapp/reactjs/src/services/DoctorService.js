import AxiosClient from "./clients/AxiosClient";

export const DoctorService = {
    get,
    getAll,
    getByClinicAndDate,
    getByClinicId,
    add,
    getNotRatedByPatient,
    rate,
    getByAdminsClinic
};


async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/doctors/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`https://localhost:8080/doctors`);
}

async function getByClinicAndDate(clinic_id, date) {
    return await AxiosClient.get(`https://localhost:8080/doctors/clinic/${clinic_id}/date/${date}`);
}

async function getByClinicId(id) {
    return await AxiosClient.get(`https://localhost:8080/doctors/clinic/${id}`);
}

async function add(doctor) {
    return await AxiosClient.post("https://localhost:8080/doctors", doctor);
}

async function getNotRatedByPatient() {
    return await AxiosClient.get(`https://localhost:8080/doctors/not_rated`);
}

async function rate(id, rating) {
    return await AxiosClient.put(`https://localhost:8080/doctors/rate/${id}`, rating);
}

async function getByAdminsClinic() {
    return await AxiosClient.get(`https://localhost:8080/doctors/admin/clinic`);
}