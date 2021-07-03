import AxiosClient from "./clients/AxiosClient";

export const AppointmentService = {
    get,
    getAll,
    update,
    finish,
    create,
    getByClinicId,
    getFreeByClinicId,
    getFreeByDoctorIdAndDate,
    deleteAppointment,
    postBookingAppointment,
    getPatientAppointments,
    getFinishedPatientAppointments
};


async function get(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/${id}`);
}

async function getAll() {
    return await AxiosClient.get(`https://localhost:8080/appointments`);
}

async function getPatientAppointments(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/patient/${id}`);
}

async function getFinishedPatientAppointments(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/patient/finished/${id}`);
}

async function update(id, appointment) {
    return await AxiosClient.put(`https://localhost:8080/appointments/${id}`, appointment);
}

async function finish(appointment) {
    return await AxiosClient.put(`https://localhost:8080/appointments/finish`, appointment);
}

async function create(appointment) {
    return await AxiosClient.post("https://localhost:8080/appointments", appointment);
}

async function getByClinicId(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/clinic/${id}`);
}

async function getFreeByClinicId(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/free/clinic/${id}`);
}

async function getFreeByDoctorIdAndDate(id, date) {
    return await AxiosClient.get(`https://localhost:8080/appointments/free/doctor/${id}/date/${date}`);
}

async function postBookingAppointment(id) {
    return await AxiosClient.post(`https://localhost:8080/appointments/booking/${id}`);
}

async function deleteAppointment(id) {
    return await AxiosClient.delete(`https://localhost:8080/appointments/${id}`);
}

