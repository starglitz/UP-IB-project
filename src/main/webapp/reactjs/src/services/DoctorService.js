import AxiosClient from "./clients/AxiosClient";

export const DoctorService = {
    get,
    getAll,
    getByClinicAndDate,
    getByClinicId,
    add
};


async function get(id) {
    return await AxiosClient.get(`http://localhost:8080/doctors/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`http://localhost:8080/doctors`);
}

async function getByClinicAndDate(clinic_id, date) {
    return await AxiosClient.get(`http://localhost:8080/doctors/clinic/${clinic_id}/date/${date}`);
}

async function getByClinicId(id) {
    return await AxiosClient.get(`http://localhost:8080/doctors/clinic/${id}`);
}

async function add(doctor) {
    return await AxiosClient.post("http://localhost:8080/doctors", doctor);
}