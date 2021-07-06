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
    getNurseAppointments,
    getDoctorAppointments,
    countAppointmentsByMonths,
    countAppointmentsByWeeks,
    getIncomeBetweenDates,
    countAppointmentsByDays,
    getFinishedPatientAppointments,
    getPatientAppointmentsHistory
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

async function getNurseAppointments(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/nurse/${id}`);
}

async function getDoctorAppointments(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/doctor/${id}`);
}

async function getFinishedPatientAppointments(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/patient/finished/${id}`);
}

async function getPatientAppointmentsHistory() {
    return await AxiosClient.get(`https://localhost:8080/appointments/appointmentHistory`);
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

async function countAppointmentsByMonths(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/count/${id}`);
}

async function countAppointmentsByWeeks(id) {
    return await AxiosClient.get(`https://localhost:8080/appointments/count/weeks/${id}`);
}
async function countAppointmentsByDays(id,month) {
    return await AxiosClient.get(`https://localhost:8080/appointments/count/days/${id}/${month}`);
}

async function getIncomeBetweenDates(id, dateFrom, dateTo) {
    return await AxiosClient.get(`https://localhost:8080/appointments/income/${id}/${dateFrom}/${dateTo}`);
}




