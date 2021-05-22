import AxiosClient from "./clients/AxiosClient";

export const AppointmentService = {
    get,
    getAll,
    update,
    create,
    getByClinicId,
    getFreeByClinicId,
    getFreeByDoctorIdAndDate,
    deleteAppointment
};


async function get(id) {
    return await AxiosClient.get(`http://localhost:8080/appointments/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`http://localhost:8080/appointments`);
}

async function update(id, appointment) {
    return await AxiosClient.put(`http://localhost:8080/appointments/${id}`, appointment);
}

async function create(appointment) {
    return await AxiosClient.post("http://localhost:8080/appointments", appointment);
}

async function getByClinicId(id) {
    return await AxiosClient.get(`http://localhost:8080/appointments/clinic/${id}`);
}

async function getFreeByClinicId(id) {
    return await AxiosClient.get(`http://localhost:8080/appointments/free/clinic/${id}`);
}

async function getFreeByDoctorIdAndDate(id, date) {
    return await AxiosClient.get(`http://localhost:8080/appointments/free/doctor/${id}/date/${date}`);
}

async function deleteAppointment(id) {
    return await AxiosClient.delete(`http://localhost:8080/appointments/${id}`);
}

