import AxiosClient from "./clients/AxiosClient";

export const ServiceService = {
    getByClinicId
};

async function getByClinicId(id) {
    return await AxiosClient.get(`https://localhost:8080/services/clinic/${id}`);
}