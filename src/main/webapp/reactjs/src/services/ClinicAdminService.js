import AxiosClient from "./clients/AxiosClient";

export const ClinicAdminService = {
    create,
};

async function create(clinicAdmin) {
    return await AxiosClient.post(`https://localhost:8080/clinic/admin`, clinicAdmin);
}
