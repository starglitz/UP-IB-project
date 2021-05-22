import AxiosClient from "./clients/AxiosClient";

export const ServiceService = {
    getByClinicId
};

async function getByClinicId(id) {
    return await AxiosClient.get(`http://localhost:8080/services/clinic/${id}`);
}